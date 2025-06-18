import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import set_calculator.RPNCalculator;
import symbolic_calculator.SymbolicCalculator;

public class Calculator {

    /**
     * Affiche le message d'aide
     */
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

    /**
     * Création d'une nouvelle instance de RPNCalculator de type T pour éviter le warning unchecked
     * 
     * @param typeClass Le type de RPNCalculator
     * @return Une nouvelle instance de RPNCalculator de type T
     */
    private static <T extends Collection<Integer>> RPNCalculator<T> createRPNCalculator(Class<T> typeClass) {
        return new RPNCalculator<>(typeClass);
    }

    /**
     * Méthode principale du projet
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici)
     */
    @SuppressWarnings("unchecked")
    public static void main(final String[] args) {
        System.out.println("Welcome to Calculator! Type `help` for more information.");
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                switch (input) {
                    case "help":
                        printHelp();
                        continue;
                    case "run 1 set":
                        System.out.println("You are in RPNCalculator set version! Type `help` for more information.");
                        try {
                            RPNCalculator<Set<Integer>> setCalculator = createRPNCalculator(TreeSet.class);
                            setCalculator.run(scanner);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        continue;
                    case "run 1 list":
                        System.out.println("You are in RPNCalculator list version! Type `help` for more information.");
                        try {
                            RPNCalculator<List<Integer>> listCalculator = createRPNCalculator(ArrayList.class);
                            listCalculator.run(scanner);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        continue;
                    case "run 2":
                        System.out.println("You are in SymbolicCalculator! Type `help` for more information.");
                        SymbolicCalculator symbolicCalculator = new SymbolicCalculator();
                        symbolicCalculator.run(scanner);
                        continue;
                    case "exit":
                        scanner.close();
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        System.err.println(input + ": command not found");
                        break;
                }
            }
        }
    }

}
