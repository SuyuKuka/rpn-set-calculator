import java.util.Stack;

public class NumberOperation implements Operation {
    
    private final int value;

    public NumberOperation(int value) {
        this.value = value;
    }

    @Override
    public void apply(Stack<Integer> stack) {
        stack.push(this.value);
    }

}
