package org.example.token;

import java.util.Optional;

public enum Operator implements Comparable<Operator> {
    PLUS(Integer::sum, 0, "+", false),
    MINUS((a, b) -> a - b, 0, "-", false),
    MULTIPLY((a,b) -> a * b, 1, "*", false),
    DIVIDE((a,b) -> a / b, 1, "/", false),
    POW((a,b) -> (int) Math.pow(a, b), 2, "^", true);

    public final Operation operation;
    public final int priority;
    public final String symbol;
    public final boolean reversedOrder;

    Operator(Operation operation, int priority, String symbol, boolean reversedOrder) {
        this.operation = operation;
        this.priority = priority;
        this.symbol = symbol;
        this.reversedOrder = reversedOrder;
    }

    public static Optional<Operator> fromString(String string) {
        for (Operator operator : Operator.values()) {
            if (operator.symbol.equals(string)) {
                return Optional.of(operator);
            }
        }
        return Optional.empty();
    }

}
