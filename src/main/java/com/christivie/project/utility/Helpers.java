package com.christivie.project.utility;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Helpers {
    public static String round(double num) {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(num);
    }

    public static boolean isANumber(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
