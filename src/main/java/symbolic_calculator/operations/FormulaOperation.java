package symbolic_calculator.operations;

import java.util.Stack;

import symbolic_calculator.formulas.Formula;

public interface FormulaOperation {

    void apply(final Stack<Formula> stack);

}
