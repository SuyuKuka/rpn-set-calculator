package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Collections;

public sealed interface Formula permits ConstantFormula, UnionFormula, IntersectionFormula, 
                                    SumFormula, MinFormula, MaxFormula, DCFormula, UCFormula {
    String toString();

    Optional<Set<Integer>> eval();

    Optional<Integer> min();

    Optional<Integer> max();

    boolean contains(final int val);

    List<Formula> getSubFormulas();
    
    default Optional<Set<Integer>> evalFiniteSet() {
        Optional<Integer> potentialMin = min();
        Optional<Integer> potentialMax = max();
        if (potentialMin.isEmpty() || potentialMax.isEmpty()) {
            return Optional.empty();
        }

        int min = potentialMin.get();
        int max = potentialMax.get();
        if (min > max) {
            return Optional.of(Collections.emptySet());
        }

        Set<Integer> result = new HashSet<>();
        for (int i = min; i <= max; i++) {
            if (this.contains(i)) { 
                result.add(i);
            }
        }
        return Optional.of(result);
    }

}
