package symbolic_calculator.operations;

import java.util.Stack;
import symbolic_calculator.formulas.Formula;

public interface FormulaOperation {

    /**
     * Applique l'operation de la formule dans la pile
     * 
     * @param stack La pile
     */
    void apply(final Stack<Formula> stack);

}
