package set_calculator.operations;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import common.operation.Operation;

public class SumOperation implements Operation<Set<Integer>> {

    @Override
    public void apply(final Stack<Set<Integer>> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for sum.");
        }

        Set<Integer> set1 = stack.pop();
        Set<Integer> set2 = stack.pop();
        Set<Integer> sum = new TreeSet<>();
        for (Integer a : set1) {
            for (Integer b : set2) {
                sum.add(a + b);
            }
        }
        
        stack.push(sum);
    }

}
