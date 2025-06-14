package set_calculator.operations;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SumOperation<T extends Collection<Integer>> implements Operation<T> {

    private final Class<? extends T> typeClass;

    public SumOperation(Class<? extends T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public void apply(final Stack<T> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("not enough operands for sum.");
        }

        List<Integer> tmp = new ArrayList<>();
        T set1 = stack.pop();
        T set2 = stack.pop();
        
        // tmp.addAll(set1);
        // tmp.addAll(set2);
        for (Integer a : set1) {
            for (Integer b : set2) {
                tmp.add(a + b);
            }
        }

        Collections.sort(tmp);
        System.out.println("TMP: " + tmp);
        try {
            T sum = typeClass.getDeclaredConstructor().newInstance();
            sum.addAll(tmp);
            stack.push(sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
