package symbolic_calculator.formulas;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record ConstantFormula(int value) implements Formula {

    /**
     * Créer un ensemble contenant la valeur de la constante
     * 
     * @return Un ensemble contenant la valeur de la constante, 
     * Optional permet d'avoir un ensemble nul ou infini
     */
    @Override
    public Optional<Set<Integer>> eval() {
        return Optional.of(new HashSet<>(Collections.singleton(value)));
    }

    /**
     * Retourne la valeur de la constante (il est le minimum de l'ensemble)
     * 
     * @return La valeur de la constante
     */
    @Override
    public Optional<Integer> min() {
        return Optional.of(value);
    }

    /**
     * Retourne la valeur de la constante (il est le maximum de l'ensemble)
     * 
     * @return La valeur de la constante
     */
    @Override
    public Optional<Integer> max() {
        return Optional.of(value);
    }

    /**
     * Vérifie si la valeur de la constante est contenue dans l'ensemble
     * 
     * @param val La valeur de la constante
     * @return Un boolean selon si la valeur de la constante est contenue dans l'ensemble ou non
     */
    @Override
    public boolean contains(final int val) {
        return val == value;
    }

    /**
     * Renvoie la liste des sous-formules de cette formule (vide car une constante n'a pas de sous-formules)
     * 
     * @return Une liste vide
     */
    @Override
    public List<Formula> getSubFormulas() {
        return Collections.emptyList();
    }

    /**
     * Renvoie le String de la valeur de la constante
     * 
     * @return La valeur de la constante en String
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
