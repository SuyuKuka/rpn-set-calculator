package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Collections;

public record MinFormula(Formula value) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Integer> min = value.min();
        if (min.isEmpty()) {
            return Optional.of(Collections.emptySet());
        }
        return Optional.of(new HashSet<>(Collections.singleton(min.get())));
    }

    @Override
    public Optional<Integer> min() {
        return value.min();
    }

    @Override
    public Optional<Integer> max() {
        return value.min();
    }

    @Override
    public boolean contains(final int val) {
        Optional<Integer> min = value.min();
        return min.isPresent() && min.get() == val;
    }

    @Override
    public List<Formula> getSubFormulas() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return "min(" + value.toString() + ")";
    }
    
}
