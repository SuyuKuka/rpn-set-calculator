package set_calculator.operations;

import java.util.Stack;
import java.util.Collection;
import java.util.Collections;

public class MinOperation<T extends Collection<Integer>> implements Operation<T> {

    private final Class<? extends T> typeClass;

    public MinOperation(Class<? extends T> typeClass) {
        this.typeClass = typeClass;
    }
    @Override
    public void apply(final Stack<T> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("not enough operands for min.");
        }

        T set = stack.pop();
        if (set.isEmpty()) {
            throw new IllegalArgumentException("impossible to find a min in an empty set.");
        }

        Integer minVal = Collections.min(set);
        try {
            T min = typeClass.getDeclaredConstructor().newInstance();
            min.add(minVal);
            stack.push(min);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
