package org.example.token;

import java.util.Stack;

import static org.example.token.TokenType.VALUE;

public class ValueToken implements Evaluable {
    private final Integer value;

    public ValueToken(Integer value) {
        this.value = value;
    }

    @Override
    public TokenType getType() {
        return VALUE;
    }

    @Override
    public Integer evaluateFromStack(Stack<Integer> stack) {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
