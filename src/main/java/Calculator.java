import java.util.Set;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;

import set_calculator.RPNCalculator;
import symbolic_calculator.SymbolicCalculator;

public class Calculator {

    private static void printHelp() {
        System.out.println("""
            --------------------------------------------------------------------------------
            Welcome to Calculator! Here are the commands you can use:
            
            Utility Commands:
                `help`       Displays this help message.
                `run 1 opt`  Runs the calculator (opt = set / list).
                `run 2`      Runs the symbolic calculator.
                `exit`       Leave the application.
            
            Stack Display Information:
                After each operation, the current state of the stack will be displayed.
            --------------------------------------------------------------------------------"""
        );
    }

    public static void main(final String[] args) {
        System.out.println("Welcome to Calculator! Type `help` for more information.");
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                } else if (input.equals("help")) {
                    printHelp();
                    continue;
                } else if (input.equals("run 1 set")) {
                    System.out.println("You are in RPNCalculator set version! Type `help` for more information.");
                    // Pour éviter le warning alors que le type est compatible
                    @SuppressWarnings("unchecked")
                    RPNCalculator<Set<Integer>> setCalculator = new RPNCalculator<>(TreeSet.class);
                    setCalculator.run(scanner);
                    continue;
                } else if (input.equals("run 1 list")) {
                    System.out.println("You are in RPNCalculator list version! Type `help` for more information.");
                    // Pour éviter le warning alors que le type est compatible
                    @SuppressWarnings("unchecked")
                    RPNCalculator<List<Integer>> listCalculator = new RPNCalculator<>(ArrayList.class);
                    listCalculator.run(scanner);
                    continue;
                } else if (input.equals("run 2")) {
                    System.out.println("You are in SymbolicCalculator! Type `help` for more information.");
                    SymbolicCalculator symbolicCalculator = new SymbolicCalculator();
                    symbolicCalculator.run(scanner);
                    continue;
                } else if (input.equals("exit")) {
                    scanner.close();
                    System.out.println("Goodbye!");
                    System.exit(0);
                } else {
                    System.err.println(input + ": command not found");
                }
            }
        }
    }

}
