import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Calculator {

    private final Stack<Set<Integer>> stack = new Stack<>();
    private final Map<String, Operation> operationsMap;

    public Calculator() {
        operationsMap = new HashMap<>();
        operationsMap.put("U", new UnionOperation());
        operationsMap.put("I", new IntersectionOperation());
        operationsMap.put("M", new SumOperation());
        operationsMap.put("min", new MinOperation());
        operationsMap.put("max", new MaxOperation());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equals("help")) {
                printHelp();
                continue;
            }

            if (input.equals("stack")) {
                printStack(stack);            
                continue;
            }

            if (input.equals("clear")) {
                stack.clear();
                System.out.println("Stack cleared.");
                continue;
            }

            if (input.equals("quit")) {
                break;
            }

            try {
                int value = Integer.parseInt(input);
                Operation constanteOp = new ConstantOperation(value);
                constanteOp.apply(stack);
            } catch (NumberFormatException e) {
                Operation operation = operationsMap.get(input);
                if (operation != null) {
                    operation.apply(stack);
                } else {
                    System.err.println("Error: invalid operation or not found or not defined (" + input + ")");
                    continue;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error operation: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            printStack(stack);            
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    public void printHelp() {
        System.out.println(
            "--------------------------------------------------------------------------------\n" + 
            "Calculator v1.0\n" + 
            "--------------------------------------------------------------------------------\n" + 
            "\n" + 
            "Welcome to Calculator! Here are the commands you can use:\n" + 
            "\n" + 
            "Set:\n" + 
            "  Just type a number (`5`, `100`, `-3`) to push it onto the stack.\n" + 
            "\n" + 
            "Operations:\n" + 
            "  `U`          Pushes the union of the top two sets onto the stack.\n" + 
            "  `I`          Pushes the intersection of the top two sets onto the stack.\n" + 
            "  `M`          Makes the sum of the top two sets onto the stack.\n" +
            "  `min`        Pushes the min of the top set onto the stack.\n" +
            "  `max`        Pushes the max of the top set onto the stack.\n" + 
            "\n" + 
            "Utility Commands:\n" + 
            "  `help`       Displays this help message.\n" +
            "  `stack`      Displays the current state of the stack.\n" +
            "  `clear`      Clears the stack.\n" +
            "  `quit`       Exits the MyShell application.\n" +
            "\n" +
            "Stack Display Information:\n" +
            "  After each operation, the current state of the stack will be displayed.\n" +
            "--------------------------------------------------------------------------------"
        );
    }

    public void printStack(Stack<Set<Integer>> stack) {
        if (!stack.isEmpty()) {
            for (int i = 0; i < stack.size(); i++) {
                if (i == stack.size() - 1) {
                    System.out.print(stack.get(i));
                    continue;
                }
                System.out.print(stack.get(i) + ", ");
            }
            System.out.println();
        } else {
            System.out.println("Stack empty");
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }

}
