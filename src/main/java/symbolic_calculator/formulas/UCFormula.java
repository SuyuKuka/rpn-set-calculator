package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

import common.formula.Formula;

public record UCFormula(Formula value) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> valueVal = value.eval();
        if (valueVal.isPresent() && valueVal.get().isEmpty()) {
            return Optional.of(Collections.emptySet());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> min() {
        return value.min();
    }

    @Override
    public Optional<Integer> max() {
        return Optional.empty();
    }

    @Override
    public boolean contains(final int val) {
        Optional<Integer> min = value.min();
        if (min.isEmpty()) {
            Optional<Set<Integer>> valueVal = value.eval();
            if (valueVal.isPresent() && valueVal.get().isEmpty()) {
                return false;
            }
            return false;
        }
        return val >= min.get();
    }

    @Override
    public List<Formula> getSubFormulas() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return "UC(" + value.toString() + ")";
    }

}
