package me.aakrylov.sandbox.testdata;

import lombok.experimental.UtilityClass;
import me.aakrylov.sandbox.model.Element;
import me.aakrylov.sandbox.model.StuffContainer;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class TestData {

    private static final LocalDateTime dateTime = LocalDateTime.of(2021, 12, 17, 11, 37, 12);

    public static StuffContainer stuffContainer() {
        return StuffContainer.builder()
                .stringField("STRING")
                .intField(1)
                .ldtField(dateTime)
                .elementField(element())
                .elementList(List.of(element()))
                .stringList(List.of("STRINGLIST_STRING_1", "STRINGLIST_STRING_2", "STRINGLIST_STRING_3"))
                .build();
    }

    public static StuffContainer stuffContainerWithEmptyStringListInElementList() {
        return StuffContainer.builder()
                .stringField("STRING")
                .intField(1)
                .ldtField(dateTime)
                .elementList(List.of(elementWithoutStringList()))
                .elementField(element())
                .stringList(List.of("STRINGLIST_STRING_1", "STRINGLIST_STRING_2", "STRINGLIST_STRING_3"))
                .build();
    }

    public static Element element() {
        return Element.builder()
                .stringField("ELEMENT_STRING")
                .intField(2)
                .ldtField(dateTime.minusDays(2))
                .stringList(List.of("ELEMENT_STRINGLIST_STRING_1", "ELEMENT_STRINGLIST_STRING_2", "ELEMENT_STRINGLIST_STRING_3"))
                .build();
    }

    public static Element elementWithoutStringList() {
        return Element.builder()
                .stringField("ELEMENT_STRING")
                .intField(2)
                .ldtField(dateTime.minusDays(2))
                .build();
    }
}
