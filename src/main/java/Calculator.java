import java.util.Scanner;
import set_calculator.SetCalculator;
import symbolic_calculator.SymbolicCalculator;

public class Calculator {

    private static void printHelp() {
        System.out.println("""
            --------------------------------------------------------------------------------
            Welcome to Calculator! Here are the commands you can use:
            
            Utility Commands:
                `help`       Displays this help message.
                `run 1/2`    Runs the calculator (1 for set calculator, 2 for symbolic calculator).
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
                } else if (input.equals("run 1")) {
                    SetCalculator setCalculator = new SetCalculator();
                    setCalculator.run(scanner);
                    continue;
                } else if (input.equals("run 2")) {
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
