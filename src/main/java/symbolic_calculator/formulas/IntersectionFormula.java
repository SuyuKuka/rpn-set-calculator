package symbolic_calculator.formulas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;

public record IntersectionFormula(Formula left, Formula right) implements Formula {

    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> leftVal = left.eval();
        Optional<Set<Integer>> rightVal = right.eval();
        if (leftVal.isEmpty() || rightVal.isEmpty()) {
            return Optional.empty();
        }

        Set<Integer> intersection = new HashSet<>(leftVal.get());
        intersection.retainAll(rightVal.get());
        return Optional.of(intersection);
    }

    @Override
    public Optional<Integer> min() {
        Optional<Integer> leftMin = left.min();
        Optional<Integer> rightMin = right.min();
        if (leftMin.isEmpty() || rightMin.isEmpty()) {
            return Optional.empty();
        }

        Optional<Integer> leftMax = left.max();
        Optional<Integer> rightMax = right.max();
        if (leftMax.isPresent() && rightMax.isPresent()) {
            if (leftMax.get() < rightMin.get() || rightMax.get() < leftMin.get()) {
                return Optional.empty();
            }
        }
        return Optional.of(Math.max(leftMin.get(), rightMin.get()));
    }

    @Override
    public Optional<Integer> max() {
        Optional<Integer> leftMax = left.max();
        Optional<Integer> rightMax = right.max();
        if (leftMax.isEmpty() || rightMax.isEmpty()) {
            return Optional.empty();
        }

        Optional<Integer> leftMin = left.min();
        Optional<Integer> rightMin = right.min();
        if (leftMin.isPresent() && rightMin.isPresent()) {
            if (leftMax.get() < rightMin.get() || rightMax.get() < leftMin.get()) {
                return Optional.empty();
            }
        }
        return Optional.of(Math.min(leftMax.get(), rightMax.get()));
    }

    @Override
    public boolean contains(final int val) {
        return left.contains(val) && right.contains(val);
    }

    @Override
    public List<Formula> getSubFormulas() {
        return List.of(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " I " + right.toString() + ")";
    }

}
