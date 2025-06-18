package symbolic_calculator.operations;

import java.util.Stack;
import symbolic_calculator.formulas.DCFormula;
import symbolic_calculator.formulas.Formula;

public class DCFormulaOperation implements FormulaOperation {

    /**
     * Pop une formule de la pile et push la formule DC de la formule dans la pile 
     * (DC : l’ensemble contenant tous les entiers inférieurs ou égaux à l’un des éléments de l’opérande)
     * 
     * @param stack La pile
     */
    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for dc.");
        }
        
        Formula formula = stack.pop();
        stack.push(new DCFormula(formula));
    }
    
}
