package org.example.token;

import java.util.Optional;

public enum Operator implements Comparable<Operator> {
    PLUS(Integer::sum, 0, "+"),
    MINUS((a, b) -> a - b, 0, "-"),
    MULTIPLY((a,b) -> a * b, 1, "*"),
    DIVIDE((a,b) -> a / b, 1, "/");

    public final Operation operation;
    public final int priority;
    public final String symbol;

    Operator(Operation operation, int priority, String symbol) {
        this.operation = operation;
        this.priority = priority;
        this.symbol = symbol;
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
