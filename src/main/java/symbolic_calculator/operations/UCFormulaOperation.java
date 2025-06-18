package symbolic_calculator.operations;

import java.util.Stack;
import symbolic_calculator.formulas.Formula;
import symbolic_calculator.formulas.UCFormula;

public class UCFormulaOperation implements FormulaOperation {

    /**
     * Pop une formule de la pile et push la formule UC de la formule dans la pile (UC : l’ensemble contenant tous les entiers supérieurs ou égaux à l’un des éléments de l’opérande)
     * 
     * @param stack La pile
     */
    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for uc.");
        }
        
        Formula formula = stack.pop();
        stack.push(new UCFormula(formula));
    }
    
}
