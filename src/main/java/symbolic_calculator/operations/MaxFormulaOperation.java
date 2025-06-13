package symbolic_calculator.operations;

import java.util.Stack;

import common.formula.Formula;
import common.operation.Operation;
import symbolic_calculator.formulas.MaxFormula;

public class MaxFormulaOperation implements Operation<Formula> {

    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for max.");
        }

        Formula value = stack.pop();
        stack.push(new MaxFormula(value));
    }

}
