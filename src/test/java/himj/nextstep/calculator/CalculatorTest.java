package himj.nextstep.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    Calculator cal;

    @BeforeEach
    void setUp() {
         cal = new Calculator();
    }

    @Test
    public void divide() {
        assertEquals(2, cal.divide(8, 4));
        System.out.println(cal.divide(8,4));
    }

    @Test
    public void multiply() {
        assertEquals(21, cal.multiply(3,7));
        System.out.println(cal.multiply(3,7));
    }

    @Test
    public void substract() {
        assertEquals(1, cal.subtract(5,4));
        System.out.println(cal.subtract(5,4));
    }

    @Test
    public void add() {
        assertEquals(7, cal.add(3,4));
        System.out.println(cal.add(3,4));
    }

    @AfterEach
    void tearDown() {
        System.out.println("tear Down!");
    }
}