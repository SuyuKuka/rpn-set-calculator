import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private final Stack<Integer> stack = new Stack<>();
    private final Map<String, Operation> operationsMap;

    public Calculator() {
        operationsMap = new HashMap<>();
        operationsMap.put("+", new AddOperation());
        operationsMap.put("-", new SubOperation());
        operationsMap.put("*", new MulOperation());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equals("quit")) {
                break;
            }

            try {
                int value = Integer.parseInt(input);
                Operation numberOperation = new NumberOperation(value);
                numberOperation.apply(stack);
            } catch (NumberFormatException e) {
                Operation operation = operationsMap.get(input);
                if (operation != null) {
                    operation.apply(stack);
                } else {
                    System.out.println("Error: invalid operation or not found or not defined ( " + input + ")");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error operation: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (!stack.isEmpty()) {
                System.out.println(stack);
            } else {
                System.out.println("Stack empty");
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }

}
