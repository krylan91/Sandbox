package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListModificationTests {

    private static final Logger logger = LoggerFactory.getLogger(ListModificationTests.class);

    @Test
    void modifyList(){
//        List<String> list = new ArrayList<>();
//        Если выбрать эту реализацию, то можно изменять размер списка во время итерации по нему
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> it = list.iterator();

        while(it.hasNext()){
            logger.info("list is: {}", list);
            String str = it.next();
            logger.info("Str is: {}", str);
            if(str.equals("2"))list.remove("5");
            if(str.equals("3"))list.add("3 found");
            // Код ниже не вызовет ConcurrentModificationException
            // т.к. он не меняет размер списка и, следовательно, modCount
            if(str.equals("4")) list.set(1, "4");
        }
    }

}
