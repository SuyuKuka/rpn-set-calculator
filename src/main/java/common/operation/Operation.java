package common.operation;

import java.util.Stack;

public interface Operation<T> {

    void apply(final Stack<T> stack);

}
