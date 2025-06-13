package set_calculator.operations;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import common.operation.Operation;

public class IntersectionOperation implements Operation<Set<Integer>> {

    @Override
    public void apply(final Stack<Set<Integer>> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for intersection.");
        }

        Set<Integer> set1 = stack.pop();
        Set<Integer> set2 = stack.pop();
        Set<Integer> intersection = new TreeSet<>(set1);
        intersection.retainAll(set2);
        stack.push(intersection);
    }
    
}
