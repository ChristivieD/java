package com.christivie.demo.day6;

import com.christivie.demo.day2.BankAccount;
import java.util.*;

import static com.christivie.demo.day6.MyCollection.printCollection;

public class MutableLists {
    private static List<BankAccount> mylist3;

    public static void main(String[] args) {
        // as.List() returns a List which is an interface,
        //The add method is unsupported because it is abstract and has ni implementation
       // mylist3 = Arrays.asList(new BankAccount(200));
        mylist3 = new ArrayList<>(Arrays.asList(new BankAccount( 200)));
        mylist3.add(new BankAccount(540));
        printCollection(mylist3, "vertical");
    }
}
