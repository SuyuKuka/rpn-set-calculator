package symbolic_calculator.memento;

import java.util.Stack;

import symbolic_calculator.formulas.Formula;

public class HistoryOperation {

    private final Stack<Formula> historyStack;

    public HistoryOperation(final Stack<Formula> stack) {
        this.historyStack = new Stack<>();
        this.historyStack.addAll(stack);
    }

    public Stack<Formula> restore() {
        Stack<Formula> tmp = new Stack<>();
        tmp.addAll(this.historyStack);
        return tmp;
    }

}
