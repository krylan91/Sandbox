package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.LinkedList;

@SpringBootTest
public class LinkedListLengthTests {

    private static final Logger logger = LoggerFactory.getLogger(LinkedListLengthTests.class);

    @Test
    void getLinkedListLength() {
        LinkedList<Integer> initialList = new LinkedList<>();
        initialList.add(1);
        initialList.add(13);
        initialList.add(76);
        initialList.add(267);
        initialList.add(89);
        initialList.add(54);
        initialList.add(311);
        initialList.add(500);
        initialList.add(13);
        initialList.add(92);

        Iterator<Integer> lengthIterator = initialList.iterator();
        Iterator<Integer> middleIterator = initialList.iterator();
        Integer length = 0;
        Integer middle = 0;

        while (lengthIterator.hasNext()) {
            lengthIterator.next();
            length += 1;
            if (length % 2 == 0) {
                middle = middleIterator.next();
            }
        }

        if (middle % 2 == 1) {
            middle = middleIterator.next();
        }

        logger.info("Length of list is {}", length);
        logger.info("Middle of list is {}", middle);

    }
}
