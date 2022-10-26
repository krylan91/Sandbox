package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringTests {

    @Test
    void shouldSubstring() {
        String testString = "1234567890";
        String result = testString.substring(0, 5);

        assertEquals(5, result.length());
        assertEquals("12345", result);
    }

    @Test
    void shouldBeEqualReference() {
        String str1 = "hello!";
        String str2 = "hello!";

        assertEquals(str1, str2);
        assertSame(str1, str2);
    }

    @Test
    void shouldNotBeEqualReference() {
        String str1 = new String("hello!");
        String str2 = new String("hello!");

        assertEquals(str1, str2);
        assertNotSame(str1, str2);
    }

    @Test
    void shouldBeSameReference() {
        String str1 = "hello!";
        String str2 = new String("hello!");

        assertEquals(str1, str2);
        assertNotSame(str1, str2);

        String str2interned = str2.intern();

        assertEquals(str1, str2interned);
        assertSame(str1, str2interned);
    }

    @Test
    void shouldSplitStrings() {
        String str1 = "[https://jira.epam.com https://jiraeu.epam.com https://jirapct.epam.com ]";
        String str2 = "[https://ms.crm.epam.com, https://crm.epam.com ]";
        String str3 = "[https://crm.epam.com , https://ms.crm.epam.com]";
        String str4 = "[https://people.epam.com]";
        String str11 = str1.replaceAll("[\\[\\]]", "");
        String pattern = ",\\s|,|\\s,\\s|\\s";
        String[] arr1 = str11.split(pattern);
        String[] arr2 = str2.split(pattern);
        String[] arr3 = str3.split(pattern);
        String[] arr4 = str4.split(pattern);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
    }

    @Test
    void shouldReturnCityName() {
        String countryCode = "NL";
        String agentName = "Access to Internet Service NL Amsterdam";
        int indexOfCountryCode = agentName.lastIndexOf(countryCode);
        String cityName = agentName.substring(indexOfCountryCode + 3).trim();

        assertThat(cityName).isEqualTo("Amsterdam");
    }
}
