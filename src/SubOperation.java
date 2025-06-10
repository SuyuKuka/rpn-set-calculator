import java.util.Stack;

public class SubOperation implements Operation {

    @Override
    public void apply(Stack<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for subtraction.");
        }
        
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a - b);
    }

}
