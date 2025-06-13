package symbolic_calculator.operations;

import java.util.Stack;

import common.formula.Formula;
import common.operation.Operation;
import symbolic_calculator.formulas.SumFormula;

public class SumFormulaOperation implements Operation<Formula> {

    @Override
    public void apply(final Stack<Formula> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for sum.");
        }

        Formula left = stack.pop();
        Formula right = stack.pop();
        stack.push(new SumFormula(left, right));
    }

}
