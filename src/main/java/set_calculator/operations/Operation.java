package set_calculator.operations;

import java.util.Collection;
import java.util.Stack;

public interface Operation<T extends Collection<Integer>> {

    /**
     * Applique l'operation sur la pile
     * 
     * @param stack La pile
     */
    void apply(final Stack<T> stack);

}
