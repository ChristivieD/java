package day8;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyMaps {
    public static void main(String[] args) {
//        Map<String, Integer> population = new HashMap<>();
//        to order your output use the treemap
        Map<String, Integer> population = new TreeMap<>();
        population.put("Des Moines", 214133);
        population.put("Cedar Rapids", 130000);
        population.put("Davenport", 101000);
        population.put("Sioux Rapids", 85000);
        population.put("Cedar Rapids", 140000);
        population.entrySet().forEach(System.out::println);
        System.out.println();
        population.forEach((city, size) -> System.out.println(city + ": " + size));
        System.out.println();
        for(Map.Entry entry: population.entrySet()){
            System.out.println(entry.getKey() + "=> "+ entry.getValue());

        }
        System.out.println();

        for (String key: population.keySet()){
            System.out.println(key + "😎😎😎" + population.get(key));
        }

        // Map states to their tree
        Map<String, String> stateTrees = new TreeMap<>();
        stateTrees.put("Wisconsin", "Sugar Maple");
        stateTrees.put("Illinois", "White Oak");
        stateTrees.put("Vermont", "Sugar Maple");
        stateTrees.put("West Virginia", "Sugar Maple");
        stateTrees.put("Maryland", "White Oak");
        stateTrees.put("New York", "Sugar Maple");
        stateTrees.put("Connecticut", "White Oak");

//        Map states tree to the count
        Map<String, Integer> treeCount = new TreeMap<>();
        for(String state: stateTrees.keySet()){
            String tree = stateTrees.get(state);
            if (treeCount.containsKey(tree)){
                // if this is the first time the tree is found
                treeCount.put(tree, 1);
            }else {
                // if the tree was previously found
                treeCount.put(tree, treeCount.get(tree) + 1);
            }
        }
        treeCount.entrySet().forEach(System.out::println);
    }
}
