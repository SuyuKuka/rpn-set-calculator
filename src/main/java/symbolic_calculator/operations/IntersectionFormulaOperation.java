package symbolic_calculator.operations;

import java.util.Stack;

import symbolic_calculator.formulas.Formula;
import symbolic_calculator.formulas.IntersectionFormula;

public class IntersectionFormulaOperation implements FormulaOperation {

    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for intersection.");
        }
        
        Formula left = stack.pop();
        Formula right = stack.pop();
        stack.push(new IntersectionFormula(left, right));
    }
    
}
