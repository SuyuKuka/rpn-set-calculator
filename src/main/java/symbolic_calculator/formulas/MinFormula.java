package symbolic_calculator.formulas;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record MinFormula(Formula formula) implements Formula {

    /**
     * Créer un ensemble contenant la valeur min de la formule si elle n'est pas infini ou indéfini
     * Sinon créer un ensemble vide
     *
     * @return Un ensemble contenant la valeur min de la formule
     */
    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Integer> min = formula.min();
        if (min.isEmpty()) {
            return Optional.of(Collections.emptySet());
        }
        return Optional.of(new HashSet<>(Collections.singleton(min.get())));
    }

    /**
     * Renvoie la valeur min de la formule
     * 
     * @return La valeur min de la formule
     */
    @Override
    public Optional<Integer> min() {
        return formula.min();
    }

    /**
     * Renvoie la valeur min de la formule car la valeur max d'un ensemble est égal à la valeur min
     * 
     * @return La valeur min de la formule
     */
    @Override
    public Optional<Integer> max() {
        return formula.min();
    }

    /**
     * Vérifie si la valeur est contenue dans l'ensemble de la formule min
     * 
     * @param val La valeur à vérifier
     * @return True si la valeur est contenue dans l'ensemble, false sinon
     */
    @Override
    public boolean contains(final int val) {
        Optional<Integer> min = formula.min();
        return min.isPresent() && min.get() == val;
    }

    /**
     * Renvoie la liste des sous-formules de la formule min (contient juste la formule passée en paramètre)
     * 
     * @return Une liste contenant la formule passée en paramètre
     */
    @Override
    public List<Formula> getSubFormulas() {
        return List.of(formula);
    }

    /**
     * Renvoie le String du min de la formule avec la valeur de la formule
     * 
     * @return La formule de l'intersection en String 
     */
    @Override
    public String toString() {
        return "min(" + formula.toString() + ")";
    }
    
}
