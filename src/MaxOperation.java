import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MaxOperation implements Operation {

    @Override
    public void apply(Stack<Set<Integer>> stack) {
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
