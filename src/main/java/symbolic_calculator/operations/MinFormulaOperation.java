package symbolic_calculator.operations;

import java.util.Stack;
import symbolic_calculator.formulas.Formula;
import symbolic_calculator.formulas.MinFormula;

public class MinFormulaOperation implements FormulaOperation {

    /**
     * Pop une formule de la pile et push la formule du min de la formule dans la pile
     * 
     * @param stack La pile
     */
    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for min.");
        }

        Formula formula = stack.pop();
        stack.push(new MinFormula(formula));
    }

}
