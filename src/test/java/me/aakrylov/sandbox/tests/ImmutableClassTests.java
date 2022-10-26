package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ImmutableClassTests {

    private static final Logger logger = LoggerFactory.getLogger(ImmutableClassTests.class);

    public static final class ImmutableClass {
        private final int index;
        private final String title;
        private final HashMap<String, Integer> someMap;

        public ImmutableClass(int index, String title, HashMap<String, Integer> someMap) {
            this.index = index;
            this.title = title;
//            Так мы делаем копию мапы, и потому внутри immutable-класса работаем с копией, которая никак не зависит от модификаций внешней мапы
            this.someMap = new HashMap<>(someMap);
//            Так мы передаём указатель на внешнюю мапу, и т.о. внутри immutable-объекта у нас не неизменяемая копия, а лишь указатель.
//            Соответственно, внутренняя мапа в immutable-классе подвержена модификациям извне.
//            this.someMap = someMap;
        }

        public int getIndex() {
            return index;
        }

        public String getTitle() {
            return title;
        }

        public HashMap<String, Integer> getSomeMap() {
//            Так мы делаем копию внутренней мапы и передаём её наружу. Соответственно, что бы ни происходило с копией, внутреннюю мапу это не затронет.
            return (HashMap<String, Integer>) someMap.clone();
//            Так мы передаём указатель на внутреннюю мапу, делая её уязвимой для изменений.
//            return someMap;
        }
    }

    @Test
    void deepCopyTest() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("First", 1);
        map.put("Second", 2);

        String title = "Original";

        int index = 10;

        ImmutableClass immutable = new ImmutableClass(index, title, map);

        logger.info("Title == immutable.title: {}", title == immutable.getTitle());
        logger.info("Index == immutable.index: {}", index == immutable.getIndex());
        logger.info("Mp == immutable.map: {}", map == immutable.getSomeMap());

        logger.info("[ORIGINAL] Immutable index = {}", immutable.getIndex());
        logger.info("[ORIGINAL] Immutable title = {}", immutable.getTitle());
        logger.info("[ORIGINAL] Immutable someMap = {}", immutable.getSomeMap());

//        В данном случае это не имеет смысла, так как внутри immutable-класса эти поля финальные и изменениям не подлежат.
        index = 20;
        title = "Modified";
        map.put("Third", 3);

        logger.info("[MODIFIED] Immutable index = {}", immutable.getIndex());
        logger.info("[MODIFIED] Immutable title = {}", immutable.getTitle());
        logger.info("[MODIFIED] Immutable someMap = {}", immutable.getSomeMap());

        HashMap<String, Integer> retrievedMap = immutable.getSomeMap();
        retrievedMap.put("Fourth", 4);

        logger.info("[MODIFIED AFTER RETRIEVE] Immutable someMap = {}", immutable.getSomeMap());
    }
}
