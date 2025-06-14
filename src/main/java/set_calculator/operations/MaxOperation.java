package set_calculator.operations;

import java.util.Stack;
import java.util.Collection;
import java.util.Collections;

public class MaxOperation<T extends Collection<Integer>> implements Operation<T> {

    private final Class<? extends T> typeClass;

    public MaxOperation(final Class<? extends T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public void apply(final Stack<T> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for max.");
        }

        T set = stack.pop();
        if (set.isEmpty()) {
            throw new IllegalArgumentException("impossible to find a max in an empty set.");
        }

        Integer maxVal = Collections.max(set);
        try {
            T max = typeClass.getDeclaredConstructor().newInstance();
            max.add(maxVal);
            stack.push(max);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

}
