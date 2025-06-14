package symbolic_calculator.operations;

import java.util.Stack;

import symbolic_calculator.formulas.Formula;
import symbolic_calculator.formulas.SumFormula;

public class SumFormulaOperation implements FormulaOperation {

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
