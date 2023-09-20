package org.sat.solver.z;

import java.util.ArrayList;
import java.util.List;

public class SatSolverZ {
    public static boolean isSatisfiable(final String formula, final List<String> literals, final List<String> negatedLiterals) {
        final List<String> andSplitted = List.of(formula.split("and"));
        final List<String> clauses = List.of(andSplitted.get(1).trim().split("\n"));

        final List<List<Boolean>> possibleEvaluations = generateAllEvaluations(literals);
        int truthyClauses = 0;
        for (List<Boolean> evaluation : possibleEvaluations) {
            for (String clause : clauses) {
                if (evaluateClause(clause, evaluation, literals, negatedLiterals)) {
                    truthyClauses++;
                }
            }
            if (truthyClauses == clauses.size()) {
                return true;
            } else {
                truthyClauses = 0;
            }
        }

        return false;
    }

    public static List<List<Boolean>> generateAllEvaluations(List<String> literals) {
        List<List<Boolean>> evaluations = new ArrayList<>();
        generateEvaluationsRecursive(literals, 0, new ArrayList<>(), evaluations);
        return evaluations;
    }

    private static void generateEvaluationsRecursive(List<String> literals, int index, List<Boolean> currentEvaluation, List<List<Boolean>> evaluations) {
        if (index == literals.size()) {
            evaluations.add(new ArrayList<>(currentEvaluation));
        } else {
            currentEvaluation.add(true);
            generateEvaluationsRecursive(literals, index + 1, currentEvaluation, evaluations);
            currentEvaluation.remove(currentEvaluation.size() - 1);

            currentEvaluation.add(false);
            generateEvaluationsRecursive(literals, index + 1, currentEvaluation, evaluations);
            currentEvaluation.remove(currentEvaluation.size() - 1);
        }
    }

    public static boolean evaluateClause(final String clause, final List<Boolean> literalValues, final List<String> literals, final List<String> negatedLiterals) {
        for (int i = 0; i < literalValues.size(); i++) {
            if (literalValues.get(i) && clause.contains(" " + literals.get(i))) {
                return true;
            } else if (!literalValues.get(i) && clause.contains(negatedLiterals.get(i))) {
                return true;
            }
        }
        return false;
    }
}
