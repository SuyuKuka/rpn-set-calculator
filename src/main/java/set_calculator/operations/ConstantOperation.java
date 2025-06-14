package set_calculator.operations;

import java.util.Stack;
import java.util.Collection;

public class ConstantOperation<T extends Collection<Integer>> implements Operation<T> {

    private final int value;
    private final Class<? extends T> typeClass;

    public ConstantOperation(final int value, final Class<? extends T> typeClass) {
        this.value = value;
        this.typeClass = typeClass;
    }

    @Override
    public void apply(final Stack<T> stack) {
        try {
            T set = typeClass.getDeclaredConstructor().newInstance();
            set.add(this.value);
            stack.push(set);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
