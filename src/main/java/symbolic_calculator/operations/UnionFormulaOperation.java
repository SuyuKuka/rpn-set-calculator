package symbolic_calculator.operations;

import java.util.Stack;

import symbolic_calculator.formulas.Formula;
import symbolic_calculator.formulas.UnionFormula;

public class UnionFormulaOperation implements FormulaOperation {

    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for union.");
        }

        Formula left = stack.pop();
        Formula right = stack.pop();
        stack.push(new UnionFormula(left, right));
    }

}
