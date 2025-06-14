import java.util.Set;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collection;

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

    // Méthode pour créer un calculateur RPN (et pour éviter le warning unchecked)
    private static <T extends Collection<Integer>> RPNCalculator<T> createRPNCalculator(Class<T> typeClass) {
        return new RPNCalculator<>(typeClass);
    }

    @SuppressWarnings("unchecked")
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
                    try {
                        RPNCalculator<Set<Integer>> setCalculator = createRPNCalculator(TreeSet.class);
                        setCalculator.run(scanner);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    continue;
                } else if (input.equals("run 1 list")) {
                    System.out.println("You are in RPNCalculator list version! Type `help` for more information.");
                    try {
                        RPNCalculator<List<Integer>> listCalculator = createRPNCalculator(ArrayList.class);
                        listCalculator.run(scanner);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
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
