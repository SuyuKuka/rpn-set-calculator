package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Collections;

import common.formula.Formula;

public record MaxFormula(Formula value) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Integer> max = value.max();
        if (max.isEmpty()) {
            return Optional.of(Collections.emptySet());
        }
        return Optional.of(new HashSet<>(Collections.singleton(max.get())));
    }

    @Override
    public Optional<Integer> min() {
        return value.max();
    }

    @Override
    public Optional<Integer> max() {
        return value.max();
    }

    @Override
    public boolean contains(final int val) {
        Optional<Integer> max = value.max();
        return max.isPresent() && max.get() == val;
    }

    @Override
    public List<Formula> getSubFormulas() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return "max(" + value.toString() + ")";
    }
    
}
