
import java.util.Scanner;
import set_calculator.SetCalculator;
import symbolic_calculator.SymbolicCalculator;

public class Calculator {

    public static void printHelp() {
        System.out.println("""
                --------------------------------------------------------------------------------
                Welcome to Calculator! Here are the commands you can use:
                
                Utility Commands:
                    `help`       Displays this help message.
                    `run 1/2`    Runs the calculator (1 for set calculator, 2 for symbolic calculator).
                    `quit`       Leave the application.
                
                Stack Display Information:
                    After each operation, the current state of the stack will be displayed.
                --------------------------------------------------------------------------------"""
        );
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Calculator! Type `help` for more information.");
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

            if (input.equals("run 1")) {
                SetCalculator setCalculator = new SetCalculator();
                setCalculator.run(scanner);
                continue;
            }

            if (input.equals("run 2")) {
                SymbolicCalculator symbolicCalculator = new SymbolicCalculator();
                symbolicCalculator.run(scanner);
                continue;
            }

            if (input.equals("quit")) {
                break;
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

}
