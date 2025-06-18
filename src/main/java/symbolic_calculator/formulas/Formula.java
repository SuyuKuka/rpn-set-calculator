package symbolic_calculator.formulas;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public sealed interface Formula permits ConstantFormula, UnionFormula, IntersectionFormula, 
                                    SumFormula, MinFormula, MaxFormula, DCFormula, UCFormula {

    /**
     * Évalue une formule et retourne un ensemble d'entiers 
     * 
     * @return Un Optional contenant un ensemble de valeurs si l'évaluation de la formule retourne un ensemble de valeurs
     * Sinon retourne un Optional vide
     */
    Optional<Set<Integer>> eval();

    /**
     * Renvoie la valeur min de la formule
     * 
     * @return Un Optional Integer selon si on a un ensemble vide ou non
     */
    Optional<Integer> min();


    /**
     * Renvoie la valeur max de la formule
     * 
     * @return Un Optional Integer selon si on a un ensemble vide ou non
     */
    Optional<Integer> max();

    /**
     * Vérifie si la valeur de la constante est contenue dans l'ensemble
     * 
     * @param val La valeur de la constante
     * @return Un boolean selon si la valeur de la constante est contenue dans l'ensemble ou non
     */
    boolean contains(final int val);

    /**
     * Renvoie la liste des sous-formules de la formule
     * 
     * @return Une liste de sous-formules
     */
    List<Formula> getSubFormulas();

    /**
     * Renvoie le String de la formule
     * 
     * @return La formule en String
     */
    @Override
    String toString();
    
    default Optional<Set<Integer>> evalFiniteSet() {
        Optional<Integer> potentialMin = min();
        Optional<Integer> potentialMax = max();
        if (potentialMin.isEmpty() || potentialMax.isEmpty()) {
            return Optional.empty();
        }

        int min = potentialMin.get();
        int max = potentialMax.get();
        if (min > max) {
            return Optional.of(Collections.emptySet());
        }

        Set<Integer> result = new HashSet<>();
        for (int i = min; i <= max; i++) {
            if (this.contains(i)) { 
                result.add(i);
            }
        }
        return Optional.of(result);
    }

}
