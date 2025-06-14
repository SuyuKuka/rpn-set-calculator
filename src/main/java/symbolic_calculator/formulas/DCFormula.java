package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

public record DCFormula(Formula value) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> val = value.eval();
        if (val.isPresent() && val.get().isEmpty()) {
            return Optional.of(Collections.emptySet());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> min() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> max() {
        return value.max();
    }

    @Override
    public boolean contains(final int val) {
        Optional<Integer> max = value.max();
        if (max.isEmpty()) {
            Optional<Set<Integer>> valueVal = value.eval();
            if (valueVal.isPresent() && valueVal.get().isEmpty()) {
                return false;
            }
            return false;
        }
        return val <= max.get();
    }

    @Override
    public List<Formula> getSubFormulas() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return "DC(" + value.toString() + ")";
    }
    
}
