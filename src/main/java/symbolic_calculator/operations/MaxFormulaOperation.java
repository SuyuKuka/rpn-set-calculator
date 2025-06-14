package symbolic_calculator.operations;

import java.util.Stack;

import symbolic_calculator.formulas.Formula;
import symbolic_calculator.formulas.MaxFormula;

public class MaxFormulaOperation implements FormulaOperation {

    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for max.");
        }

        Formula value = stack.pop();
        stack.push(new MaxFormula(value));
    }

}
