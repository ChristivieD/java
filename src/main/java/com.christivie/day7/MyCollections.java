package com.christivie.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCollections {
    public static void main(String[] args) {
        List<String> programmingLanguges = new ArrayList<>(Arrays.asList("Python", "C#", "Java", "PHP", "Cobol"));
        List<String> copy = new ArrayList<>(programmingLanguges);
        // filter anything that doesn't start with the letter p
        copy.removeIf((language) -> language.toLowerCase().charAt(0) != 'P');
        copy.forEach(System.out::println);
        System.out.println();
        programmingLanguges.forEach(System.out::println);
    }
}
