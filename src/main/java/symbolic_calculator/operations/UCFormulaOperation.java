package symbolic_calculator.operations;

import java.util.Stack;

import common.formula.Formula;
import common.operation.Operation;
import symbolic_calculator.formulas.UCFormula;

public class UCFormulaOperation implements Operation<Formula> {

    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for uc.");
        }
        
        Formula value = stack.pop();
        stack.push(new UCFormula(value));
    }
    
}
