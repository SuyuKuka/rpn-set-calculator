import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SumOperation implements Operation {

    @Override
    public void apply(Stack<Set<Integer>> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for sum.");
        }

        Set<Integer> set1 = stack.pop();
        Set<Integer> set2 = stack.pop();
        Set<Integer> sum = new HashSet<>();
        for (Integer a : set1) {
            for (Integer b : set2) {
                sum.add(a + b);
            }
        }
        stack.push(sum);
    }

}
