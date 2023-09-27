import java.util.HashMap;
import java.util.Map;

public class Quiz {
    public static void main(String[] args) {
        Map<String, Integer> groceryList = new HashMap<>();
        groceryList.put("milk", 1);
        groceryList.put("bread", 2);
        groceryList.put("eggs", 1);

//        for (String key : groceryList.keySet()) {
//            System.out.println(key + "=" + groceryList.get(key));
//        }


//        groceryList.forEach((key, value) -> System.out.println(key + "=" + value));
//
//
//        for (Map.Entry entry : groceryList.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
//
//
//        for(int i = 0; i < groceryList.size(); i++) {
//            System.out.println(groceryList.get(i) + "=" + i);
//        }
//
//
//        groceryList.entrySet().forEach(System.out::println);
    }
}
