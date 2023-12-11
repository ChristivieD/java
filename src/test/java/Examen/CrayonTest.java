package Examen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrayonTest {

    @Test
    public void getColor() {
        assertEquals("black", new Crayon().getColor());
    }

    @Test
    void setColor() {
        String expected = "red";
        Crayon crayon = new Crayon();
        crayon.setColor("red");
        String actual = crayon.getColor();
        assertEquals(expected, actual);
    }

    @Test
    void getPercentRemaining() {
        assertEquals(1, new Crayon().getPercentRemaining());
    }

    @Test
    void setPercentRemaining() {
        double expected = 1.0;
        Crayon crayon = new Crayon();
        crayon.setPercentRemaining(1.0);
        double actual = new Crayon().getPercentRemaining();
        assertEquals(expected,actual);

    }

    @Test
    void compareTo() {
    }

    @Test
    void testToString() {
    }
}