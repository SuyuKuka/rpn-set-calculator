import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ConstantOperation implements Operation {

    private final int value;

    public ConstantOperation(int value) {
        this.value = value;
    }

    @Override
    public void apply(Stack<Set<Integer>> stack) {
        Set<Integer> singleton = new HashSet<>(Collections.singleton(this.value));
        stack.push(singleton);
    }

}
