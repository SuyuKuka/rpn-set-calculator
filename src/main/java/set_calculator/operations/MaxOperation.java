package set_calculator.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class MaxOperation<T extends Collection<Integer>> implements Operation<T> {

    private final Class<? extends T> typeClass;

    public MaxOperation(final Class<? extends T> typeClass) {
        this.typeClass = typeClass;
    }

    /**
     * Pop un ensemble de la pile et push la valeur max de l'ensemble dans la pile
     * 
     * @param stack La pile
     */
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
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        
    }

}
