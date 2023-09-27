package com.christivie.day6;

import java.util.ArrayList;
import java.util.Iterator;

public class Quiz {
    public static void main(String[] args) {
        ArrayList<String> groceryList = new ArrayList<String>();
        groceryList.add("milk");
        groceryList.add("bread");
        groceryList.add("bread");
        groceryList.add("eggs");
        groceryList.add("bread");
        ArrayList<String> groceryListCopy = new ArrayList(groceryList);


        Iterator<String> it = groceryList.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (str.equals("bread"))
                it.remove();
        }

        System.out.println(groceryList);
    }
}
