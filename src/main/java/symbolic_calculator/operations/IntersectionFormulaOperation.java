package symbolic_calculator.operations;

import java.util.Stack;
import symbolic_calculator.formulas.Formula;
import symbolic_calculator.formulas.IntersectionFormula;

public class IntersectionFormulaOperation implements FormulaOperation {

    /**
     * Pop deux formules de la pile et push la formule de l'intersection des deux formules dans la pile
     * 
     * @param stack La pile
     */
    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for intersection.");
        }
        
        Formula formula1 = stack.pop();
        Formula formula2 = stack.pop();
        stack.push(new IntersectionFormula(formula1, formula2));
    }
    
}
