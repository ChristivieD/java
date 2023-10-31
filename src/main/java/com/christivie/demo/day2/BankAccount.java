package com.christivie.demo.day2;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    // Assume a class BankAccount exists with an instance variable called "balance" (a double).
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Write a toString method that could print "Your balance on May X, 20XX is $1,234.56".
    // Substitute "May X, 20XX" with today's date. Substitute "1,234.56" with the balance.
    //You must apply date and number formatting to earn full credit.
    @Override
    public String toString() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(" MMMM d, yyyy ");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return " your balance on" + today.format(dateFormat) + " is" + currencyFormat.format(balance);
    }
}

//Dsecribr hoe oyu call a static method
// you call static mothods by tytping a class name, a period, then the method name
// example1 : localdate.noe(0
// example2 : DatetimeFormatter. of pattern()
// ypu call non-static methods by typing an object name, aperiod, then the method name
// Example 1: today.format()
// Example 2: currencyFormat.format()
