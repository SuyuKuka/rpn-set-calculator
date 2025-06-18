package symbolic_calculator.formulas;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record MaxFormula(Formula formula) implements Formula {

    /**
     * Créer un ensemble contenant la valeur max de la formule si elle n'est pas infini ou indéfini
     * Sinon créer un ensemble vide
     *
     * @return Un ensemble contenant la valeur max de la formule
     */
    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Integer> max = formula.max();
        if (max.isEmpty()) {
            return Optional.of(Collections.emptySet());
        }
        return Optional.of(new HashSet<>(Collections.singleton(max.get())));
    }

    /**
     * Renvoie la valeur max de la formule car la valeur min d'un ensemble est égal à la valeur max
     * 
     * @return La valeur max de la formule
     */
    @Override
    public Optional<Integer> min() {
        return formula.max();
    }

    /**
     * Renvoie la valeur max de la formule
     * 
     * @return La valeur max de la formule
     */
    @Override
    public Optional<Integer> max() {
        return formula.max();
    }

    /**
     * Vérifie si la valeur est contenue dans l'ensemble de la formule max
     * 
     * @param val La valeur à vérifier
     * @return True si la valeur est contenue dans l'ensemble, false sinon
     */
    @Override
    public boolean contains(final int val) {
        Optional<Integer> max = formula.max();
        return max.isPresent() && max.get() == val;
    }

    /**
     * Renvoie la liste des sous-formules de la formule max (contient juste la formule passée en paramètre)
     * 
     * @return Une liste contenant la formule passée en paramètre
     */
    @Override
    public List<Formula> getSubFormulas() {
        return List.of(formula);
    }

    /**
     * Renvoie le String du max de la formule avec la valeur de la formule
     * 
     * @return La formule de l'intersection en String 
     */
    @Override
    public String toString() {
        return "max(" + formula.toString() + ")";
    }
    
}
