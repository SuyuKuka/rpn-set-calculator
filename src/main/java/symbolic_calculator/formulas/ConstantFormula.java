package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Collections;

import common.formula.Formula;

public record ConstantFormula(int value) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        return Optional.of(new HashSet<>(Collections.singleton(value)));
    }

    @Override
    public Optional<Integer> min() {
        return Optional.of(value);
    }

    @Override
    public Optional<Integer> max() {
        return Optional.of(value);
    }

    @Override
    public boolean contains(final int val) {
        return val == value;
    }

    @Override
    public List<Formula> getSubFormulas() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
