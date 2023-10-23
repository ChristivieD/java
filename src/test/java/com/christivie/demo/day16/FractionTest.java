package com.christivie.demo.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    @Test
    public void defaultConstructor(){
        String expected = "1/1";
        String actual = new Fraction().toString();
        assertEquals(expected, actual);

    }
    @Test
    public void parameterizedConstructor(){
        Fraction fraction = new Fraction(27, 6);
        String expected = "27/6";
        String actual = fraction.toString();
        assertEquals(expected,actual);

    }
    @Test
    public void getNumerator(){
        int expected = 1;
        int actual = new Fraction().getNumerator();
        assertEquals(expected,actual);
        assertEquals(27,new Fraction(27,6).getNumerator());
    }
    @Test
    public void getDenominator(){
        assertEquals(1,new Fraction().getDenominator());
        assertEquals(6,new Fraction(27,6).getDenominator());
    }
    @Test
    public void setDenominator(){
        int expected = 30;
        Fraction fraction = new Fraction();
        fraction.setDenominator(30);
        int actual = fraction.getDenominator();
        assertEquals(expected, actual);
        assertThrows(Exception.class,() -> fraction.setDenominator(0));

    }
    @Test
    public void setNumerator(){
        int expected = 30;
        Fraction fraction = new Fraction();
        fraction.setNumerator(30);
        int actual = fraction.getNumerator();
        assertEquals(expected, actual);
    }
    @Test
    public void greatestCommonFactor(){
        int actual = Fraction.greatestCommonDivisor(75,45);
        int expected = 15;
        assertTrue(expected == actual);
        assertEquals(2, Fraction.greatestCommonDivisor(2,4));
        assertTrue(1 == Fraction.greatestCommonDivisor(5,7));
    }
    @Test
    public void testDouble(){
        double expected = 3.3;
        double actual = 1.1 + 2.2;
        assertEquals(expected,actual,0.001);
    }
    @Test
    public  void simplify() {
        // partner 2
        Fraction fraction = new Fraction(2, 4);
        String expected = "1/2";
        String actual = fraction.simplify().toString();
        assertEquals(expected, actual);


        Fraction fraction2 = new Fraction(-2, 4);
        String expected1 = "-1/2";
        String actual1 = fraction2.simplify().toString();
        assertTrue(expected1.equals(actual1));

        Fraction fraction3 = new Fraction(-2, -4);
        Fraction simplify = fraction3.simplify();
        assertEquals(simplify.getNumerator(), 1);
        assertTrue(simplify.getDenominator() == 2);

        // 1
        assertEquals(new Fraction(75,45).simplify().toString(), "5/3");
        // 3
        assertTrue(new Fraction(5,7).toString().equals("5/7"));
        // 5
        Fraction newFraction = new Fraction(2,-4);
        Fraction simplifiedFraction = newFraction.simplify();
        assertEquals(simplifiedFraction.getNumerator(), -1);
        assertTrue(simplifiedFraction.getDenominator() == 2);


    }
    @Test
    public void mixedNumber(){
        assertEquals(new Fraction(4,1).mixedNumber().toString(), "4");
        assertEquals(new Fraction(0,4).mixedNumber().toString(),"0");
        assertEquals(new Fraction(4,4).mixedNumber().toString(),"1");
        assertEquals(new Fraction(8,4).mixedNumber().toString(),"2");
        assertEquals(new Fraction(4,8).mixedNumber().toString(),"1/2");
        assertEquals(new Fraction(13,5).mixedNumber().toString(),"2 3/5");
        assertEquals(new Fraction(-13,5).mixedNumber().toString(),"-2 3/5");
        assertEquals(new Fraction(13,-5).mixedNumber().toString(),"-2 3/5");
        assertEquals(new Fraction(-13,-5).mixedNumber().toString(),"2 3/5");
        assertEquals(new Fraction(-4,-5).mixedNumber().toString(),"4/5");

    }
}