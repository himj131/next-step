package himj.nextstep.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    private StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    @DisplayName("구분자를 쉼표(,)로 전달할 경우 숫자의 합 반환")
    void commaSeparatorTest() {
        int result = stringCalculator.add("1,2,3");

        assertEquals(6, result);
    }


    @Test
    @DisplayName("구분자를 콜론(:)으로 전달할 경우 숫자의 합 반환")
    void colonSeparatorTest() {
        int result = stringCalculator.add("1:2:3");

        assertEquals(6, result);
    }

    @Test
    @DisplayName("빈값을 입력한 경우 0 반환")
    void inputNullThenReturnZero() {
        assertEquals(0,  stringCalculator.add("  "));
        assertEquals(0,  stringCalculator.add(null));
    }

    @Test
    @DisplayName("커스텀 구분자가 있을때 숫자의 합 반환")
    void customSeparatorTest() {
        int result = stringCalculator.add("//;\n1;2;3");

        assertEquals(6, result);
    }

    @Test
    @DisplayName("음수 입력하면 RuntimeException")
    void negativeIntTest() {
        assertThrows(RuntimeException.class, () -> stringCalculator.add("3:2:-2"));
    }
}