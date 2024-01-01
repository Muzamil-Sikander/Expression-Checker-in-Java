public class BracketChecker {
    private static class HardcodedStack {
        private char[] stackArray;
        private int top;

        public HardcodedStack(int capacity) {
            stackArray = new char[capacity];
            top = -1;
        }

        public void push(char symbol) {
            if (top == stackArray.length - 1) {
                System.out.println("Stack Overflow: Cannot push " + symbol + ". Stack is full.");
            } else {
                stackArray[++top] = symbol;
            }
        }

        public char pop() {
            if (top == -1) {
                System.out.println("Stack Underflow: Cannot pop. Stack is empty.");
                return '\0'; // Return a placeholder for an empty stack
            } else {
                return stackArray[top--];
            }
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    private static boolean isMatching(char left, char right) {
        return (left == '(' && right == ')') || (left == '{' && right == '}') || (left == '[' && right == ']');
    }

    private static boolean isValidExpression(String expression) {
        HardcodedStack stack = new HardcodedStack(expression.length());

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);

            if (symbol == '(' || symbol == '{' || symbol == '[') {
                stack.push(symbol);
            } else if (symbol == ')' || symbol == '}' || symbol == ']') {
                if (stack.isEmpty()) {
                    System.out.println("Invalid Expression: Right brackets are more than the left brackets.");
                    return false;
                }

                char popped = stack.pop();
                if (!isMatching(popped, symbol)) {
                    System.out.println("Invalid Expression: Mismatched brackets.");
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("Valid Expression: Brackets are well balanced.");
            return true;
        } else {
            System.out.println("Invalid Expression: Left brackets are more than the right brackets.");
            return false;
        }
    }

    public static void main(String[] args) {
        String expression1 = "(A-B+c)}";
        String expression2 = "{(A+B)- (C/D)}";
        String expression3 = "[A/b-(c*d)+(e-f)+(c-d))";

        isValidExpression(expression1);
        isValidExpression(expression2);
        isValidExpression(expression3);
    }
}
