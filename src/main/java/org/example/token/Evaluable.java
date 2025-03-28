package org.example.token;

import java.util.Stack;

public interface Evaluable {
    TokenType getType();

    Integer evaluateFromStack(Stack<Integer> stack);
}
