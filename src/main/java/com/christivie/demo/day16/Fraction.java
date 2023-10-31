package com.christivie.demo.day16;

import java.math.BigInteger;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction() {
        this.numerator = 1;
        this.denominator = 1;
    }


    public Fraction(int numerator, int denominator) {
        setNumerator(numerator);
        setDenominator(denominator);
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    public int getNumerator(){
        return this.numerator;
    }

    public void setNumerator (int numerator){
        this.numerator=numerator;
    }

    public int getDenominator(){
        return this.denominator;
    }

    public void setDenominator (int denominator){
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator can not be zero");
        }
        this.denominator = denominator;
    }


    public static int greatestCommonDivisor(int num1, int num2){
        BigInteger i = BigInteger.valueOf(num1).gcd(BigInteger.valueOf(num2));
        int gcd = Integer.parseInt(i.toString());
        return gcd;
    }
    public Fraction simplify() {
        int gcd = greatestCommonDivisor(numerator, denominator);
        Fraction simplifiedFraction = new Fraction(numerator / gcd, denominator / gcd);
        if ((simplifiedFraction.numerator >= 0 && simplifiedFraction.denominator < 0) || (simplifiedFraction.numerator < 0 && simplifiedFraction.denominator < 0)) {
            simplifiedFraction.numerator *= -1;
            simplifiedFraction.denominator *= -1;

        }
        return simplifiedFraction;
    }
    public String mixedNumber(){
        Fraction fraction = simplify();
        int quotient = fraction.numerator / fraction.denominator;
        int remainder = fraction.numerator % fraction.denominator;
        if(fraction.denominator == 1 || fraction.numerator == 0){
            return Integer.toString(fraction.numerator);
        }else if(fraction.numerator > fraction.denominator){
            if(remainder == 0){
                return Integer.toString(quotient);
            }
            else{
                return String.format("%d %d/%d", quotient,remainder, fraction.denominator);
            }
        }else if(fraction.numerator < 0 && Math.abs(fraction.numerator) > fraction.denominator){
            if(remainder == 0){
                return Integer.toString(quotient);
            }
            else{
                return String.format("%d %d/%d", quotient,remainder * -1, fraction.denominator);
            }
        } else if(fraction.numerator == fraction.denominator){
            return "1";
        } else{
            return String.format("%d/%d", fraction.numerator, fraction.denominator);
        }

    }
}
