package set_calculator.operations;

import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import java.util.Collections;

import common.operation.Operation;

public class ConstantOperation implements Operation<Set<Integer>> {

    private final int value;

    public ConstantOperation(final int value) {
        this.value = value;
    }

    @Override
    public void apply(final Stack<Set<Integer>> stack) {
        stack.push(new HashSet<>(Collections.singleton(this.value)));
    }

}
