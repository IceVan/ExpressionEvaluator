package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExpressionEvaluatorTest {

    @ParameterizedTest
    @CsvSource({
            "'3 + 3', 6",
            "'3 * 2 - 1', 5",
            "'5 / 9 * 10 + -1', -1",
            "'3 + 2 - 1 * 4 / 2', 3",
            "'10 - 3 + 2 * 5 / 2', 12",
            "'-3 + 8 - 2 * -4 + 6 / 2', 16",
            "'5 * 3 - 8 / 2 + 7 - 6', 12",
            "'100 / 5 * 3 - 7 + 2 - 8', 47"
    })
    public void testValidExpressions(String expression, int expectedResult) {
        assertEquals(expectedResult, ExpressionEvaluator.evaluate(expression));
    }

    @Test
    public void testNoOperatorsShouldThrowExpressionEvaluatorException() {
        assertThrows(ExpressionEvaluatorException.class, () -> ExpressionEvaluator.evaluate("4 5 6"));
    }

    @Test
    public void testTooManyOperandsShouldThrowExpressionEvaluatorException() {
        assertThrows(ExpressionEvaluatorException.class, () -> ExpressionEvaluator.evaluate("4 + 5 6"));
    }

    @Test
    public void testNoOperandsShouldThrowExpressionEvaluatorException() {
        assertThrows(ExpressionEvaluatorException.class, () -> ExpressionEvaluator.evaluate("+ -"));
    }

    @Test
    public void testNonIntegerShouldThrowExpressionEvaluatorException() {
        assertThrows(ExpressionEvaluatorException.class, () -> ExpressionEvaluator.evaluate("4 + 7.56 - 1"));
    }

    @Test
    public void testNonIntegerShouldThrowExpressionEvaluatorException2() {
        assertThrows(ExpressionEvaluatorException.class, () -> ExpressionEvaluator.evaluate("4 + 7.56 - asd"));
    }
}
