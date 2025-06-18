package symbolic_calculator.formulas;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record UCFormula(Formula formula) implements Formula {

    /**
     * Éformula cette formule UC et retourne un ensemble d'entiers si la formule représente un ensemble vide
     *
     * @return Un Optional contenant un ensemble vide si l'évaluation de la formule retourne un ensemble vide
     * Sinon retourne un Optional vide (=la formule représente un ensemble infini ou indéfini)
     */
    @Override
    public Optional<Set<Integer>> eval() {
        Optional<Set<Integer>> val = formula.eval();
        if (val.isPresent() && val.get().isEmpty()) {
            return Optional.of(Collections.emptySet());
        }
        return Optional.empty();
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
     * La formule UC ne contient pas de maximum car elle est composée d'un ensemble infini d'entiers
     * 
     * @return Un Optional vide
     */
    @Override
    public Optional<Integer> max() {
        return Optional.empty();
    }

    /**
     * Vérifie si la valeur est contenue dans l'ensemble décrit par la formule UC
     * 
     * @param val La valeur à vérifier
     * @return True si la valeur est contenue dans l'ensemble, false sinon
     */
    @Override
    public boolean contains(final int val) {
        Optional<Integer> min = formula.min();
        if (min.isEmpty()) {
            Optional<Set<Integer>> formulaVal = formula.eval();
            if (formulaVal.isPresent() && formulaVal.get().isEmpty()) {
                return false;
            }
            return false;
        }
        return val >= min.get();
    }

    /**
     * Renvoie la liste des sous-formules de la formule UC (contient juste la formule passée en paramètre)
     * 
     * @return Une liste contenant la formule passée en paramètre
     */
    @Override
    public List<Formula> getSubFormulas() {
        return List.of(formula);
    }

    /**
     * Renvoie le String de la formule UC avec la valeur de la formule
     * 
     * @return La formule UC en String
     */
    @Override
    public String toString() {
        return "UC(" + formula.toString() + ")";
    }

}
