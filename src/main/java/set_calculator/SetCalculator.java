package set_calculator;

import common.operations.Operation;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import set_calculator.operation.*;

public class SetCalculator {

    private final Stack<Set<Integer>> stack = new Stack<>();
    private final Map<String, Operation> operationsMap;

    public SetCalculator() {
        operationsMap = new HashMap<>();
        operationsMap.put("U", new UnionOperation());
        operationsMap.put("I", new IntersectionOperation());
        operationsMap.put("M", new SumOperation());
        operationsMap.put("min", new MinOperation());
        operationsMap.put("max", new MaxOperation());
    }

    public void run(Scanner scanner) {
        System.out.println("You are in SetCalculator! Type `help` for more information.");

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
                System.out.println("Returning to main application.");
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
    }

    public void printHelp() {
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
            
            Stack Display Information:
                After each operation, the current state of the stack will be displayed.
            --------------------------------------------------------------------------------"""
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

}
