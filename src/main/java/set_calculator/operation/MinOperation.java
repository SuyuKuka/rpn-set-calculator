package set_calculator.operation;

import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import java.util.Collections;

import common.operations.Operation;

public class MinOperation implements Operation {

    @Override
    public void apply(Stack<Set<Integer>> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for min.");
        }

        Set<Integer> set = stack.pop();
        if (set.isEmpty()) {
            throw new IllegalArgumentException("impossible to find a min in an empty set.");
        }

        Integer min = Collections.min(set);
        stack.push(new HashSet<>(Collections.singleton(min)));
    }

}
