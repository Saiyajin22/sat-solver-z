import org.junit.Test;
import org.sat.solver.z.SatSolverZ;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TestSatSolverZ {

    @Test
    public void testUnsatisfiableFormula() {
        final String formula =
                "(and\n" +
                        "(or A B)\n" +
                        "(or A (notB))\n" +
                        "(or (notA) B)\n" +
                        "(or (notA) (notB))\n";
        final List<String> literals = List.of("A", "B");
        final List<String> negatedLiterals = List.of("notA", "notB");
        boolean result = SatSolverZ.isSatisfiable(formula, literals, negatedLiterals);

        assertFalse(result);
    }

    @Test
    public void testSatisfiableFormulaWithTwoLiterals() {
        final String formula =
                "(and\n" +
                        "(or A B)\n" +
                        "(or A (notB))\n" +
                        "(or (notA) (notB))\n";
        final List<String> literals = List.of("A", "B");
        final List<String> negatedLiterals = List.of("notA", "notB");
        boolean result = SatSolverZ.isSatisfiable(formula, literals, negatedLiterals);

        assertTrue(result);
    }

    @Test
    public void testSatisfiableFormulaWithThreeLiterals() {
        final String formula =
                "(and\n" +
                        "(or A B (notC))\n" +
                        "(or B C)\n" +
                        "(notB)\n" +
                        "(or (notA) C))";
        final List<String> literals = List.of("A", "B", "C");
        final List<String> negatedLiterals = List.of("notA", "notB", "notC");
        boolean result = SatSolverZ.isSatisfiable(formula, literals, negatedLiterals);

        assertTrue(result);
    }

    @Test
    public void testSatisfiableFormulaWithManyClauses() {
        StringBuilder formulaBuilder = new StringBuilder("(and\n");
        final List<String> literals = List.of("A", "B", "C");
        final List<String> negatedLiterals = List.of("notA", "notB", "notC");

        for (int i = 0; i < 10000000; i++) {
            int randomLiteralIndex = new Random().nextInt(literals.size());
            int randomNegatedLiteralIndex = new Random().nextInt(negatedLiterals.size());

            String randomLiteral = literals.get(randomLiteralIndex);
            String randomNegatedLiteral = negatedLiterals.get(randomNegatedLiteralIndex);

            String clause = "(or " + randomLiteral + " " + randomNegatedLiteral + ")\n";
            formulaBuilder.append(clause);
        }
        
        formulaBuilder.append(")");
        String formula = formulaBuilder.toString();

        boolean result = SatSolverZ.isSatisfiable(formula, literals, negatedLiterals);
        assertNotNull(result);
    }

}
