package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;

import common.formula.Formula;

public record SumFormula(Formula left, Formula right) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> leftVal = left.eval();
        Optional<Set<Integer>> rightVal = right.eval();
        if (leftVal.isEmpty() || rightVal.isEmpty()) {
            return Optional.empty();
        }

        Set<Integer> set1 = leftVal.get();
        Set<Integer> set2 = rightVal.get();
        Set<Integer> sum = new HashSet<>();
        for (Integer a : set1) {
            for (Integer b : set2) {
                sum.add(a + b);
            }
        }
        return Optional.of(sum);
    }

    @Override
    public Optional<Integer> min() {
        Optional<Integer> leftMin = left.min();
        Optional<Integer> rightMin = right.min();
        if (leftMin.isEmpty() || rightMin.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(leftMin.get() + rightMin.get());
    }

    @Override
    public Optional<Integer> max() {
        Optional<Integer> leftMax = left.max();
        Optional<Integer> rightMax = right.max();
        if (leftMax.isEmpty() || rightMax.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(leftMax.get() + rightMax.get());
    }

    @Override
    public boolean contains(final int val) {
        Optional<Set<Integer>> leftSet = left.eval();
        Optional<Set<Integer>> rightSet = right.eval();
        if (leftSet.isPresent() && rightSet.isPresent()) {
            for (Integer a : leftSet.get()) {
                if (rightSet.get().contains(val - a)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Formula> getSubFormulas() {
        return List.of(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " M " + right.toString() + ")";
    }

}
