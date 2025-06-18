package symbolic_calculator.formulas;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record DCFormula(Formula formula) implements Formula {

    /**
     * Éformula cette formule DC et retourne un ensemble d'entiers si la formule représente un ensemble vide
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
     * La formule DC ne contient pas de minimum car elle est composée d'un ensemble infini d'entiers
     * 
     * @return Un Optional vide
     */
    @Override
    public Optional<Integer> min() {
        return Optional.empty();
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
     * Vérifie si la valeur est contenue dans l'ensemble décrit par la formule DC
     * 
     * @param val La valeur à vérifier
     * @return True si la valeur est contenue dans l'ensemble, false sinon
     */
    @Override
    public boolean contains(final int val) {
        Optional<Integer> max = formula.max();
        if (max.isEmpty()) {
            Optional<Set<Integer>> formulaVal = formula.eval();
            if (formulaVal.isPresent() && formulaVal.get().isEmpty()) {
                return false;
            }
            return false;
        }
        return val <= max.get();
    }

    /**
     * Renvoie la liste des sous-formules de la formule DC (contient juste la formule passée en paramètre)
     * 
     * @return Une liste contenant la formule passée en paramètre
     */
    @Override
    public List<Formula> getSubFormulas() {
        return List.of(formula);
    }

    /**
     * Renvoie le String de la formule DC avec la valeur de la formule
     * 
     * @return La formule DC en String
     */
    @Override
    public String toString() {
        return "DC(" + formula.toString() + ")";
    }
    
}
