package set_calculator.operations;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import common.operation.Operation;

public class UnionOperation implements Operation<Set<Integer>> {
    
    @Override
    public void apply(final Stack<Set<Integer>> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for union.");
        }

        Set<Integer> set1 = stack.pop();
        Set<Integer> set2 = stack.pop();
        Set<Integer> union = new TreeSet<>(set1);
        union.addAll(set2);
        stack.push(union);
    }
    
}
