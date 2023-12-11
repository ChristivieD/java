package Examen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crayon implements Comparable <Crayon> {
    private String color;
    private double percentRemaining;

    public Crayon() {
        this.color = "black";
        this.percentRemaining = 1.0;
    }

    public Crayon(String color, double percentRemaining) {
        setColor(color);
        setPercentRemaining(percentRemaining);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPercentRemaining() {
        return percentRemaining;
    }

    public void setPercentRemaining(double percentRemaining) {
        if (percentRemaining < 0 || percentRemaining > 1) {
            throw new IllegalArgumentException("Percent remaining must be between 0 and 1.");
        }
        this.percentRemaining = percentRemaining;
    }

    @Override
    public int compareTo(Crayon other) {
        int colorComparison = this.color.compareTo(other.color);
        if (colorComparison != 0) {
            return colorComparison;
        } else {
            return Double.compare(other.percentRemaining, this.percentRemaining);
        }
    }

    @Override
    public String toString() {
        return "Examen.Crayon{" +
                "color='" + color + '\'' +
                ", percentRemaining=" + percentRemaining +
                '}';
    }

    public static void main(String[] args) {
        List<Crayon> crayons = new ArrayList<>();
        crayons.add(new Crayon("red", 0.8));
        crayons.add(new Crayon("blue", 0.6));
        crayons.add(new Crayon("green", 1.0));
        crayons.add(new Crayon("red", 0.4));
        crayons.add(new Crayon("yellow", 0.9));

        Collections.sort(crayons);
        System.out.println("Sorted List: " + crayons);

        List<Crayon> copy = new ArrayList<>(crayons);
        copy.removeIf(c -> c.getPercentRemaining() < 0.5);
        System.out.println("Copy with more than 50% remaining: " + copy);

        Collections.sort(crayons, Collections.reverseOrder());
        System.out.println("Reverse Sorted List: " + crayons);
    }
}
