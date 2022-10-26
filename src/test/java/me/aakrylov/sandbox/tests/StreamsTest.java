package me.aakrylov.sandbox.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.aakrylov.sandbox.functionals.TriFunction;
import me.aakrylov.sandbox.model.Element;
import me.aakrylov.sandbox.model.StuffContainer;
import me.aakrylov.sandbox.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class StreamsTest {

    private List<Integer> initialList;

    @Test
    void shouldSuccessfullyModifyList() {
        initialList = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        initialList.remove(2);

        assertEquals(4, initialList.size());
        assertEquals(4, initialList.get(2));
    }

    @Test
    void shouldThrowUnsupportedOperationException() {
        initialList = Arrays.asList(1, 2, 3, 4, 5);

        assertThrows(UnsupportedOperationException.class, () -> initialList.remove(2));
    }

    @Test
    void whenNotEmptyList_shouldReturnJoinedString() {
        String expectedString = "ELEMENT_STRINGLIST_STRING_1,ELEMENT_STRINGLIST_STRING_2,ELEMENT_STRINGLIST_STRING_3";
        StuffContainer testContainer = TestData.stuffContainer();

        String actualString = testContainer.getElementList()
                .stream()
                .flatMap(el -> el.getStringList().stream())
                .collect(Collectors.joining(","));

        assertEquals(expectedString, actualString);
    }

    @Test
    void whenNullList_whenFilter_shouldReturnEmptyString() {
        String expectedString = "";
        StuffContainer testContainer = TestData.stuffContainerWithEmptyStringListInElementList();

        String actualString = testContainer.getElementList()
                .stream()
                .map(Element::getStringList)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.joining(","));

        assertEquals(expectedString, actualString);
    }

    @Test
    void whenNullList_whenNoFilter_shouldThrowNPE() {
        StuffContainer testContainer = TestData.stuffContainerWithEmptyStringListInElementList();

        Executable executable = () -> testContainer.getElementList()
                .stream()
                .flatMap(el -> el.getStringList().stream())
                .collect(Collectors.joining(","));

        assertThrows(NullPointerException.class, executable);
    }

    @Test
    void shouldSumValues() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        Integer sum = integers.stream()
                .reduce((acc, x) -> {
                    log.info("Sum is {}, current number is {}, result is {}", acc, x, acc + x);
                    return acc + x;
                })
                .get();

        assertEquals(15, sum);
    }

    @Test
    void shouldCalculateFactorial() {
        int factorialViaRecursion = factorial(6);
        int factorialViaStreams = Stream.of(1, 2, 3, 4, 5, 6)
                .reduce((acc, x) -> acc * x)
                .get();

        assertEquals(720, factorialViaRecursion);
        assertEquals(720, factorialViaStreams);
    }

    private int factorial(int i) {
        if (i == 1) {
            return 1;
        }
        return i * factorial(i - 1);
    }

    @Data
    @AllArgsConstructor
    private static class Employee {
        private String name;
        private Integer age;
        private String jobTitle;
        private Float salary;
    }

    @Test
    void challenge() {
        Employee e1 = new Employee("Andrei", 30, "developer", 230000f);
        Employee e2 = new Employee("Elena", 28, "analyst", 160000f);
        Employee e3 = new Employee("Valeriy", 30, "developer", 250000f);
        Employee e4 = new Employee("Boris", 30, "qa", 330000f);
        Employee e5 = new Employee("Nikita", 30, "analyst", 130000f);
        Employee e6 = new Employee("Dmitrii", 30, "developer", 50000f);

        List<Employee> employees = List.of(e1, e2, e3, e4, e5, e6);

        Map<String, Float> averageSalaryByJobTitles = employees.stream()
                .collect(Collectors.groupingBy(Employee::getJobTitle))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        employeesByJobTitles -> employeesByJobTitles.getValue()
                                .stream()
                                .map(Employee::getSalary)
                                .reduce(0f, Float::sum)  / employeesByJobTitles.getValue().size()));

        assertEquals(3, averageSalaryByJobTitles.size());
        assertEquals(145000f, averageSalaryByJobTitles.get("analyst"));
    }

    @Data
    @AllArgsConstructor
    static class Event {
        int day; // sorted positive
        int hour; // 0 - 23
        boolean progress; // start/end
    }

    @Test
    void shouldGetHoursOfUnavailabilityByDays() {
        Event event1 = new Event(1, 1, true);
        Event event2 = new Event(1, 2, false);
        Event event3 = new Event(1, 5, true);
        Event event4 = new Event(1, 7, false);
        Event event5 = new Event(1, 10, true);
        Event event6 = new Event(1, 12, false);

        List<Event> events = List.of(event1, event2, event3, event4, event5, event6);

        Map<Integer, Integer> hoursOfUnavailabilityByDays = new HashMap<>();
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            int day = event.getDay();
            if (hoursOfUnavailabilityByDays.containsKey(day)) {
                if (!event.progress) {
                    int endHour = event.getHour();
                    int startHour = events.get(i-1).getHour();
                    int hoursOfUnavailability = endHour - startHour;
                    int storedHoursOfUnavailability = hoursOfUnavailabilityByDays.get(day);
                    hoursOfUnavailabilityByDays.put(day, storedHoursOfUnavailability + hoursOfUnavailability);
                }
            } else {
                hoursOfUnavailabilityByDays.put(day, 0);
            }
        }
        log.info("Result: {}", hoursOfUnavailabilityByDays);
    }
}
