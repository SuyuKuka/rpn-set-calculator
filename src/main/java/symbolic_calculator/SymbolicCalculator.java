package symbolic_calculator;

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Optional;

import symbolic_calculator.memento.*;
import symbolic_calculator.formulas.*;
import symbolic_calculator.operations.*;

public class SymbolicCalculator {

    private final Stack<Formula> stack = new Stack<>();
    private final Map<String, FormulaOperation> operationsMap;
    private final Stack<HistoryOperation> operationHistory = new Stack<>();

    public SymbolicCalculator() {
        operationsMap = new HashMap<>();
        operationsMap.put("U", new UnionFormulaOperation());
        operationsMap.put("I", new IntersectionFormulaOperation());
        operationsMap.put("M", new SumFormulaOperation());
        operationsMap.put("min", new MinFormulaOperation());
        operationsMap.put("max", new MaxFormulaOperation());
        operationsMap.put("UC", new UCFormulaOperation());
        operationsMap.put("DC", new DCFormulaOperation());
    }

    public void run(final Scanner scanner) {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equals("help")) {
                printHelp();
                continue;
            } else if (input.equals("stack")) {
                printStack();
                continue;
            } else if (input.equals("clear")) {
                stack.clear();
                operationHistory.clear();
                updateHistory(); 
                System.out.println("Stack cleared.");
                continue;
            } else if (input.equals("undo")) {
                undo();
                continue;
            } else if (input.equals("quit")) {
                System.out.println("Returning to main application.");
                break;
            } else if (input.equals("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            updateHistory();

            try {
                int value = Integer.parseInt(input);
                stack.push(new ConstantFormula(value));
            } catch (NumberFormatException e) {
                FormulaOperation operation = operationsMap.get(input);
                if (operation != null) {
                    operation.apply(stack);
                } else {
                    if (!operationHistory.isEmpty()) {
                        operationHistory.pop();
                    }

                    System.err.println("Error: invalid operation/command not found or not defined (" + input + ")");
                }
            } catch (IllegalArgumentException e) {
                if (!operationHistory.isEmpty()) {
                    operationHistory.pop();
                }

                System.err.println("Error operation: " + e.getMessage());
            } catch (Exception e) {
                if (!operationHistory.isEmpty()) {
                    operationHistory.pop(); 
                }
                System.err.println("Error: " + e.getMessage());
            }

            if (!stack.isEmpty()) {
                printStack();
            } else {
                System.out.println("Empty stack.");
            }
        }
    }

    private void printHelp() {
        System.out.println("""
            --------------------------------------------------------------------------------
            Welcome to SymbolicCalculator! Here are the commands you can use:
            
            Set:
                Just type a number (`5`, `100`, `-3`) to push it onto the stack.
            
            Operations:
                `U`          Pushes the union of the top two formulas onto the stack.
                `I`          Pushes the intersection of the top two formulas onto the stack.
                `M`          Makes the sum of the top two formulas onto the stack.
                `min`        Pushes the min of the top formula onto the stack.
                `max`        Pushes the max of the top formula onto the stack.
                `UC`         Pushes the upper closure of the top formula's set onto the stack.
                `DC`         Pushes the lower closure of the top formula's set onto the stack.
            
            Utility Commands:
                `help`       Displays this help message.
                `stack`      Displays the current state of the stack.
                `clear`      Clears the stack.
                `undo`       Undoes the last operation.
                `quit`       Returns to the main application.
                `exit`       Leave the application.
            
            Stack Display Information:
                After each operation, the current state of the stack will be displayed.
            --------------------------------------------------------------------------------"""
        );
    }

    private void printStack() {
        if (stack.isEmpty()) {
            System.out.println("Stack empty.");
            return;
        }

        System.out.println("Stack (Formula => Value) :");
        for (int i = 0; i < stack.size(); i++) {
            Formula formula = stack.get(i);
            Optional<Set<Integer>> result = formula.eval();
            String evalString = result.map(set -> formatSetForDisplay(set))
                                        .orElse("INFINITE / UNDEFINED");
            System.out.println(formula.toString() + " => " + evalString);
        }
    }

    private String formatSetForDisplay(Set<Integer> set) {
        if (set.isEmpty()) return "{}";
        return "{" + new TreeSet<>(set).stream()
                                        .map(Object::toString)
                                        .collect(java.util.stream.Collectors.joining(", ")) + "}";
    }

    private void updateHistory() {
        operationHistory.push(new HistoryOperation(stack));
    }

    private void undo() {
        if (!operationHistory.isEmpty()) {
            HistoryOperation history = operationHistory.pop();
            stack.clear();
            stack.addAll(history.restore());
            System.out.println("Last operation undone.");
        } 
        
        printStack();
    }

}
