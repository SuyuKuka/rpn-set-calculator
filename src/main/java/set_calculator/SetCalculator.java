package set_calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import common.operation.Operation;
import set_calculator.operations.*;

public class SetCalculator {

    private final Stack<Set<Integer>> stack = new Stack<>();
    private final Map<String, Operation<Set<Integer>>> operationsMap;

    public SetCalculator() {
        operationsMap = new HashMap<>();
        operationsMap.put("U", new UnionOperation());
        operationsMap.put("I", new IntersectionOperation());
        operationsMap.put("M", new SumOperation());
        operationsMap.put("min", new MinOperation());
        operationsMap.put("max", new MaxOperation());
    }

    public void run(final Scanner scanner) {
        System.out.println("You are in SetCalculator! Type `help` for more information.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            } else if (input.equals("help")) {
                printHelp();
                continue;
            } else if (input.equals("stack")) {
                printStack(stack);
                continue;
            } else if (input.equals("clear")) {
                stack.clear();
                System.out.println("Stack cleared.");
                continue;
            } else if (input.equals("quit")) {
                System.out.println("Returning to main application.");
                break;
            } else if (input.equals("exit")) {
                scanner.close();
                System.out.println("Goodbye!");
                System.exit(0);
            }


            try {
                int value = Integer.parseInt(input);
                Operation<Set<Integer>> constantOp = new ConstantOperation(value);
                constantOp.apply(stack);
            } catch (NumberFormatException e) {
                Operation<Set<Integer>> operation = operationsMap.get(input);
                if (operation != null) {
                    operation.apply(stack);
                } else {
                    System.err.println("Error: invalid operation/operation not found or not defined (" + input + ")");
                    continue;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error operation: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            printStack(stack);
        }
    }

    private void printHelp() {
        System.out.println("""
            --------------------------------------------------------------------------------
            Welcome to SetCalculator! Here are the commands you can use:
            
            Set:
                Just type a number (`5`, `100`, `-3`) to push it onto the stack.
            
            Operations:
                `U`          Pushes the union of the top two sets onto the stack.
                `I`          Pushes the intersection of the top two sets onto the stack.
                `M`          Makes the sum of the top two sets onto the stack.
                `min`        Pushes the min of the top set onto the stack.
                `max`        Pushes the max of the top set onto the stack.
            
            Utility Commands:
                `help`       Displays this help message.
                `stack`      Displays the current state of the stack.
                `clear`      Clears the stack.
                `quit`       Returns to the main application.
                `exit`       Leave the application.
            
            Stack Display Information:
                After each operation, the current state of the stack will be displayed.
            --------------------------------------------------------------------------------"""
        );
    }

    private void printStack(final Stack<Set<Integer>> stack) {
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

}
