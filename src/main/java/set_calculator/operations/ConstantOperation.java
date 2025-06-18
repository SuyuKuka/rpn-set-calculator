package set_calculator.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Stack;
public class ConstantOperation<T extends Collection<Integer>> implements Operation<T> {

    private final int value;
    private final Class<? extends T> typeClass;

    public ConstantOperation(final int value, final Class<? extends T> typeClass) {
        this.value = value;
        this.typeClass = typeClass;
    }

    /**
     * Push un ensemble contenant la valeur de cette constante sur la pile
     * 
     * @param stack La pile
     */
    @Override
    public void apply(final Stack<T> stack) {
        try {
            T set = typeClass.getDeclaredConstructor().newInstance();
            set.add(this.value);
            stack.push(set);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
