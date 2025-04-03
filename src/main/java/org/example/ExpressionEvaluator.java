package org.example;

import org.example.token.*;

import java.util.*;

import static org.example.token.TokenType.OPERATOR;
import static org.example.token.TokenType.VALUE;

public class ExpressionEvaluator {

    public static int evaluate(String expression){
        List<Evaluable> tokens;
        try {
            tokens = createReversedPolishNotation(expression);
            System.out.println(tokens);

        } catch (NumberFormatException e) {
            throw new ExpressionEvaluatorException("Expression can contain only integers and +, -, /, * symbols: " + expression);
        }
        Stack<Integer> stack = new Stack<>();

        try{
            for (Evaluable token : tokens) {
                if(VALUE.equals(token.getType())){
                    stack.push(token.evaluateFromStack(stack));
                } else if (OPERATOR.equals(token.getType())) {
                    var value = token.evaluateFromStack(stack);
                    stack.pop();
                    stack.pop();
                    stack.push(value);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new ExpressionEvaluatorException("Invalid expression: " + expression);
        }

        if(stack.size() > 1){
            throw new ExpressionEvaluatorException("Invalid expression (too many values in regard to operators): " + expression);
        }

        return stack.peek();
    }

    private static List<Evaluable> createReversedPolishNotation(String expression){
        StringTokenizer tokenizer = new StringTokenizer(expression);
        Stack<OperatorToken> operatorStack = new Stack<>();
        Stack<Evaluable> output = new Stack<>();

        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            Optional<Operator> operator = Operator.fromString(token);

            if(operator.isPresent()){
                while (!operatorStack.empty() && operatorStack.peek().getPriority() >= operator.get().priority && !operator.get().reversedOrder){
                    output.push(operatorStack.pop());
                }
                operatorStack.push(new OperatorToken(operator.get()));
            } else {
                output.push(new ValueToken(Integer.parseInt(token)));
            }
        }
        while(!operatorStack.empty()){
            output.push(operatorStack.pop());
        }

        return new ArrayList<>(output);
    }
}
