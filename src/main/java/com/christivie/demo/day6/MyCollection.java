package com.christivie.demo.day6;

import com.christivie.demo.day2.BankAccount;

import java.util.*;
import java.util.stream.Collectors;

public class MyCollection {
    public static void main(String[] args) {
        List<String> list1= new ArrayList<>();
//      List<String> list1 = new LinkedList<>();
//      List<String> list1 = new Vector<>();
        list1.add("banana");
        list1.add("carrot");
        list1.add(0,"apple");
        list1.addAll(Arrays.asList("apple","banana", "carrot"));
        list1.addAll(3, Arrays.asList("APPLE", "BANANA", "CARROT"));
        printCollection(list1, "horizontal");


        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount(400));
        accounts.add(new BankAccount(600));
        accounts.add(new BankAccount(200));
        accounts.add(new BankAccount(430));
        printCollection(accounts, "vertical");

        Queue<Integer> queue1 = new PriorityQueue<>();

        Set<Double> set1=  new HashSet<>();
//      Set<Double> set1 = new LinkedHashset<>();
//      Set<Double> set1 = new TreeSet<>();

        Map<String, Integer> map1 = new HashMap<>();
//      Map<String, Integer> map1 = new TreeMap<>();
//      Map<String, Integer> map1 = new Hashtable<>();

    }
    public static void printCollection(Collection<? extends Object> collection, String direction){
        if(direction.equalsIgnoreCase("vertical")){
            // Advanced for loop (aka: for-each loop)
            // for(var el: collection){
            //el stands for element, java will declare  the data type during run time
            //System.out.println(el);}

            // Use the . forEach() method from the Collection class with a lambda expression
            // collection.forEach((el) -> System.out.println(el));


            // Use the .forEach() method from the collection class with amethod reference
            collection.forEach(System.out::println);
        } else{
//            System.out.println(collection);
            // Doesn't print brackets, but print comma at the end
//            for(var el: collection){
//                System.out.println(el + ", ");
//                }
//            System.out.println();
            // Use streams to print the brackets
            System.out.println(collection.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }
    }
}
