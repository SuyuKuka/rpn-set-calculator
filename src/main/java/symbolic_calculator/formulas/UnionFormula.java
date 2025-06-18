package symbolic_calculator.formulas;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record UnionFormula(Formula formula1, Formula formula2) implements Formula {

    /**
     * Évalue cette formule d'union
     *
     * @return Un Optional contenant un ensemble d'entiers qui contient les valeurs des deux formules
     */
    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> val1 = formula1.eval();
        Optional<Set<Integer>> val2 = formula2.eval();
        if (val1.isEmpty() || val2.isEmpty()) {
            return Optional.empty();
        }

        Set<Integer> union = new HashSet<>(val1.get());
        union.addAll(val2.get());
        return Optional.of(union);
    }


    /**
     * Renvoie la valeur min de la formule d'union
     * Si l'une des formules a une valeur infinie, on renvoie la valeur min de l'autre
     * Sinon, on renvoie la valeur min entre les valeurs min des deux formules
     * 
     * @return Un Optional Integer contenant la valeur min de la formule
     * Si on a pas de min, on renvoie un Optional vide
     */
    @Override
    public Optional<Integer> min() {
        Optional<Integer> min1 = formula1.min();
        Optional<Integer> min2 = formula2.min();
        if (min1.isEmpty() && min2.isEmpty()) {
            return Optional.empty();
        } else if (min1.isEmpty()) {
            return min2;
        } else if (min2.isEmpty()) {
            return min1;
        } else {
            return Optional.of(Math.min(min1.get(), min2.get()));
        }
    }

    /**
     * Renvoie la valeur max de la formule d'union
     * Si l'une des formules a une valeur infinie, on renvoie la valeur max de l'autre
     * Sinon, on renvoie la valeur max entre les valeurs max des deux formules
     * 
     * @return Un Optional Integer contenant la valeur max de la formule
     * Si on a pas de max, on renvoie un Optional vide
     */

    @Override
    public Optional<Integer> max() {
        Optional<Integer> max1 = formula1.max();
        Optional<Integer> max2 = formula2.max();
        if (max1.isEmpty() && max2.isEmpty()) {
            return Optional.empty();
        } else if (max1.isEmpty()) {
            return max2;
        } else if (max2.isEmpty()) {
            return max1;
        } else {
            return Optional.of(Math.max(max1.get(), max2.get()));
        }
    }

    /**
     * Vérifie si la valeur est contenue dans l'ensemble de la formule d'union
     * 
     * @param val La valeur à vérifier
     * @return True si la valeur est contenue dans les deux formules, false sinon
     */
    @Override
    public boolean contains(final int val) {
        return formula1.contains(val) || formula2.contains(val);
    }

    /**
     * Renvoie la liste des sous-formules de l'union
     * 
     * @return Une liste de deux sous-formules qui correspond au deux formules qui composent l'union
     */
    @Override
    public List<Formula> getSubFormulas() {
        return List.of(formula1, formula2);
    }

    /**
     * Renvoie le String de l'union de la formule avec les deux valeurs
     * 
     * @return La formule de l'union en String 
     */
    @Override
    public String toString() {
        return "(" + formula1.toString() + " U " + formula2.toString() + ")";
    }

}
