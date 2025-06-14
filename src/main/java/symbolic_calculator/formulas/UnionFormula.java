package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;

public record UnionFormula(Formula left, Formula right) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> leftVal = left.eval();
        Optional<Set<Integer>> rightVal = right.eval();
        if (leftVal.isEmpty() || rightVal.isEmpty()) {
            return Optional.empty();
        }

        Set<Integer> union = new HashSet<>(leftVal.get());
        union.addAll(rightVal.get());
        return Optional.of(union);
    }

    @Override
    public Optional<Integer> min() {
        Optional<Integer> leftMin = left.min();
        Optional<Integer> rightMin = right.min();
        if (leftMin.isEmpty() && rightMin.isEmpty()) {
            return Optional.empty();
        } else if (leftMin.isEmpty()) {
            return rightMin;
        } else if (rightMin.isEmpty()) {
            return leftMin;
        } else {
            return Optional.of(Math.min(leftMin.get(), rightMin.get()));
        }
    }

    @Override
    public Optional<Integer> max() {
        Optional<Integer> leftMax = left.max();
        Optional<Integer> rightMax = right.max();
        if (leftMax.isEmpty() && rightMax.isEmpty()) {
            return Optional.empty();
        } else if (leftMax.isEmpty()) {
            return rightMax;
        } else if (rightMax.isEmpty()) {
            return leftMax;
        } else {
            return Optional.of(Math.max(leftMax.get(), rightMax.get()));
        }
    }

    @Override
    public boolean contains(final int val) {
        return left.contains(val) || right.contains(val);
    }

    @Override
    public List<Formula> getSubFormulas() {
        return List.of(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " U " + right.toString() + ")";
    }

}
