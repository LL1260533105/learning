package collectionAndMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    static Map<String, Object> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        map.put("","");
    }
}
