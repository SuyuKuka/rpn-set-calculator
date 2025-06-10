import java.util.Stack;

public class MulOperation implements Operation {
    
    @Override
    public void apply(Stack<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for multiplication.");
        }
        
        int a = stack.pop();
        int b = stack.pop();
        stack.push(a * b);
    }
    
}
