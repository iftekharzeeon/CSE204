import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean radixPoint = false;
        boolean radixGoing = false;
        boolean number = false;
        boolean negative = false;
        String lastIns = "";

        GenericStack<String> values = new GenericStack<>();
        GenericStack<Character> operators = new GenericStack<>();
        GenericStack<Character> parenthesis = new GenericStack<>();

        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        expression = "(" + expression + ")";

        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (c == ' ') {
                    continue;
                } if (c == '+' || c == '-' || c == '*' || c == '/') {
                    if (c != '-' && negative) {
                        System.out.println("Not Valid");
                        return;
                    }
                    if (lastIns.equals("open") && c == '-') {
                        negative = true;
                    }
                    if (operators.peek() != null && (operators.peek() == '*' || operators.peek() == '/')) {
                        if (precedenceCheck(c)) {
                            if (operators.peek() != null) {
                                while ((operators.peek() != '(')) {
                                    operation(operators, values);
                                }
                            }
                        }
                    }
                    number = false;
                    radixGoing = false;
                    lastIns = "";
                    operators.push(expression.charAt(i));

                } else if (c == '(') {
                    lastIns = "open";
                    number = false;
                    parenthesis.push(expression.charAt(i));
                    operators.push(expression.charAt(i));

                } else if (c == ')') {
                    number = false;
                    radixGoing = false;
                    if (negative) {
                        operators.pop();
                        operators.pop();
                        parenthesis.pop();
                        double temp = Double.parseDouble(values.pop());
                        temp = temp*(-1);
                        values.push(Double.toString(temp));
                        negative = false;
                        continue;
                    }
                    if (parenthesis.pop() != null) {
                        if (operators.peek() != null) {
                            while ((operators.peek() != '(' )){
                                operation(operators, values);
                            }
                        }
                        operators.pop();
                    } else {
                        System.out.println("Not Valid");
                        return;
                    }

                } else if (c == '.') {
                    radixPoint = true;
                    radixGoing = true;
                    number = false;

                } else if (c >= '0' && c <= '9') {
                    if (number) {
                        String previous = values.pop();
                        previous = previous + expression.charAt(i);
                        values.push(previous);
                        number = true;
                        continue;

                    } else if (radixPoint) {
                        String previous = values.pop();
                        previous = previous + "." + expression.charAt(i);
                        values.push(previous);
                        radixPoint = false;
                        continue;

                    } else if (radixGoing) {
                        String previous = values.pop();
                        previous = previous + expression.charAt(i);
                        values.push(previous);
                        continue;
                    }
                    values.push(Character.toString(expression.charAt(i)));
                    lastIns = "";
                    number = true;
                } else {
                    System.out.println("Not Valid");
                    return;
                }
            }
            if (!parenthesis.isEmpty() || !operators.isEmpty() || values.isEmpty()) {
                System.out.println("Not Valid");
            } else {
                System.out.print("Valid Expression, Computed value: ");
                System.out.println(values.pop());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Not Valid");
        }
    }

    private static boolean precedenceCheck(char c) {
        return c != '/' && c != '(';
    }

    private static void operation(GenericStack<Character> operators, GenericStack<String> values) {
        double first;
        double second;
        double result = 0;
        second = Double.parseDouble(values.pop());
        first = Double.parseDouble(values.pop());
        char operator = operators.pop();
        if (operators.peek() == '-') {
            first = (-1) * first;
            operators.pop();
            operators.push('+');
        }
        switch (operator) {
            case '+':
                result = first + second;
                break;
            case '-':
                result = first - second;
                break;
            case '*':
                result = first * second;
                break;
            case '/':
                result = first / second;
                break;
        }
        values.push(Double.toString(result));
    }
}
