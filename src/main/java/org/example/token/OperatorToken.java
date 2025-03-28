package org.example.token;

import java.util.Stack;

import static org.example.token.TokenType.OPERATOR;

public class OperatorToken implements Evaluable {
    private final Operator operator;

    public OperatorToken(Operator operator) {
        this.operator = operator;
    }

    public Operation getOperation() {
        return operator.operation;
    }

    public int getPriority() {
        return operator.priority;
    }

    @Override
    public TokenType getType() {
        return OPERATOR;
    }

    @Override
    public Integer evaluateFromStack(Stack<Integer> stack) {
        if (stack.size() < 2){
            throw new IllegalArgumentException("Stack has less values than required by operator");
        }

        return getOperation().compute(stack.get(stack.size()-2), stack.peek());
    }

    public String toString() {
        return operator.symbol;
    }
}
