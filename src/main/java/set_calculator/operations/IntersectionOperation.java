package set_calculator.operations;

import java.util.Stack;
import java.util.Collection;

public class IntersectionOperation<T extends Collection<Integer>> implements Operation<T> {
    
    private final Class<? extends T> collectionType;

    public IntersectionOperation(Class<? extends T> collectionType) {
        this.collectionType = collectionType;
    }

    @Override
    public void apply(final Stack<T> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for intersection.");
        }

        T set1 = stack.pop();
        T set2 = stack.pop();
        try {
            T intersection = collectionType.getDeclaredConstructor().newInstance();
            intersection.addAll(set1);
            intersection.retainAll(set2);
            stack.push(intersection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
