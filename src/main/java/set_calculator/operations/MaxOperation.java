package set_calculator.operations;

import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import java.util.Collections;

import common.operation.Operation;

public class MaxOperation implements Operation<Set<Integer>> {

    @Override
    public void apply(final Stack<Set<Integer>> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for max.");
        }

        Set<Integer> set = stack.pop();
        if (set.isEmpty()) {
            throw new IllegalArgumentException("impossible to find a max in an empty set.");
        }

        Integer max = Collections.max(set);
        stack.push(new HashSet<>(Collections.singleton(max)));
    }

}
