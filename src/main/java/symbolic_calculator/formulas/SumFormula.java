package symbolic_calculator.formulas;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record SumFormula(Formula formula1, Formula formula2) implements Formula {

    
    /**
     * Évalue cette formule de somme
     *
     * @return Un Optional contenant un ensemble d'entiers qui contient les sommes de Minkowski des deux ensembles
     * Si une des formules n'a pas de valeur, on renvoie un Optional vide
     */
    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> val1 = formula1.eval();
        Optional<Set<Integer>> val2 = formula2.eval();
        if (val1.isEmpty() || val2.isEmpty()) {
            return Optional.empty();
        }

        Set<Integer> set1 = val1.get();
        Set<Integer> set2 = val2.get();
        Set<Integer> sum = new HashSet<>();
        for (Integer a : set1) {
            for (Integer b : set2) {
                sum.add(a + b);
            }
        }
        return Optional.of(sum);
    }

    /**
     * Renvoie la valeur min de la formule de somme en sommant les valeurs min des deux formules
     * 
     * @return Un Optional Integer contenant la valeur min de la formule de somme
     * Si on a pas de min, on renvoie un Optional vide
     */
    @Override
    public Optional<Integer> min() {
        Optional<Integer> min1 = formula1.min();
        Optional<Integer> min2 = formula2.min();
        if (min1.isEmpty() || min2.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(min1.get() + min2.get());
    }

    /**
     * Renvoie la valeur max de la formule de somme en sommant les valeurs max des deux formules
     * 
     * @return Un Optional Integer contenant la valeur max de la formule de somme
     * Si on a pas de max, on renvoie un Optional vide
     */
    @Override
    public Optional<Integer> max() {
        Optional<Integer> max1 = formula1.max();
        Optional<Integer> max2 = formula2.max();
        if (max1.isEmpty() || max2.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(max1.get() + max2.get());
    }

    /**
     * Vérifie si la valeur est contenue dans l'ensemble de la formule de somme
     * En itérant sur les éléments de l'ensemble de la première formule, on vérifie si l'ensemble de la deuxième
     * contient la valeur moins l'élément courant
     *
     * @param val La valeur à vérifier
     * @return True si la valeur est contenue dans l'ensemble, false sinon
     */
    @Override
    public boolean contains(final int val) {
        Optional<Set<Integer>> set1 = formula1.eval();
        Optional<Set<Integer>> set2 = formula2.eval();
        if (set1.isPresent() && set2.isPresent()) {
            for (Integer a : set1.get()) {
                if (set2.get().contains(val - a)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Renvoie la liste des sous-formules de la somme de Minkowski
     * 
     * @return Une liste de deux sous-formules qui correspondent au deux formules qui composent la somme de Minkowski
     */
    @Override
    public List<Formula> getSubFormulas() {
        return List.of(formula1, formula2);
    }

    /**
     * Renvoie le String de la somme de Minkowski de la formule avec les deux valeurs
     * 
     * @return La formule de l'intersection en String 
     */
    @Override
    public String toString() {
        return "(" + formula1.toString() + " M " + formula2.toString() + ")";
    }

}
