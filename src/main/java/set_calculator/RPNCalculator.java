package set_calculator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import set_calculator.operations.*;

public class RPNCalculator<T extends Collection<Integer>> {

    private final Stack<T> stack;
    private final Map<String, Operation<T>> operationsMap;
    private final Class<? extends T> typeClass;

    public RPNCalculator(Class<? extends T> typeClass) {
        stack = new Stack<>();
        operationsMap = new HashMap<>();
        this.typeClass = typeClass;

        operationsMap.put("U", new UnionOperation<>(typeClass));
        operationsMap.put("I", new IntersectionOperation<>(typeClass));
        operationsMap.put("M", new SumOperation<>(typeClass));
        operationsMap.put("min", new MinOperation<>(typeClass));
        operationsMap.put("max", new MaxOperation<>(typeClass));
    }

    /**
     * Lance la calculatrice RPN
     * 
     * @param scanner Le scanner de l'utilisateur
     */
    public void run(final Scanner scanner) {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "help":
                    printHelp();
                    continue;
                case "stack":
                    printStack(stack);
                    continue;
                case "clear":
                    stack.clear();
                    System.out.println("Stack cleared.");
                    continue;
                case "quit":
                    System.out.println("Returning to main application.");
                    break;
                case "exit":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    break;
            }

            try {
                // Test si l'input est un entier
                int value = Integer.parseInt(input);
                Operation<T> constantOp = new ConstantOperation<>(value, typeClass);
                constantOp.apply(stack);
            } catch (NumberFormatException e) {
                // Test si l'input est une operation
                Operation<T> operation = operationsMap.get(input);
                if (operation != null) {
                    operation.apply(stack);
                } else {
                    System.err.println("Error: invalid operation/operation not found or not defined (" + input + ")");
                    continue;
                }
            } catch (IllegalArgumentException e) {
                // Erreur sur l'operation
                System.err.println("Error operation: " + e.getMessage());
            } catch (Exception e) {
                // Autre erreur
                System.err.println("Error: " + e.getMessage());
            }

            printStack(stack);
        }
    }

    /**
     * Affiche le message d'aide
     */
    private void printHelp() {
        System.out.println("""
            --------------------------------------------------------------------------------
            Welcome to RPNCalculator! Here are the commands you can use:
            
            Elements:
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

    /**
     * Affiche l'état actuel de la pile
     * 
     * @param stack La pile
     */
    private void printStack(final Stack<T> stack) {
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
