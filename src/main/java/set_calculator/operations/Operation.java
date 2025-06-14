package set_calculator.operations;

import java.util.Stack;
import java.util.Collection;

public interface Operation<T extends Collection<Integer>> {

    void apply(final Stack<T> stack);

}
