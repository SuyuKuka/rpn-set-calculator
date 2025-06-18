package set_calculator.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Stack;

public class UnionOperation<T extends Collection<Integer>> implements Operation<T> {
    
    private final Class<? extends T> typeClass;

    public UnionOperation(final Class<? extends T> typeClass) {
        this.typeClass = typeClass;
    }

    /**
     * Pop deux ensembles de la pile et push leur union dans la pile
     * 
     * @param stack La pile
     */
    @Override
    public void apply(final Stack<T> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for union.");
        }

        T set1 = stack.pop();
        T set2 = stack.pop();
        try {
            T union = typeClass.getDeclaredConstructor().newInstance();
            union.addAll(set1);
            union.addAll(set2);
            stack.push(union);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Error during union", e);
        }
    }
    
}
