package org.sat.solver.z;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String example1 =
                "(and\n" +
                        "(or A B (notC))\n" +
                        "(or B C)\n" +
                        "(notB)\n" +
                        "(or (notA) C))";
        final List<String> literals = List.of("A", "B", "C");
        final List<String> negatedLiterals = List.of("notA", "notB", "notC");
        System.out.println(SatSolverZ.isSatisfiable(example1, literals, negatedLiterals));

        final String example2 =
                "(and\n" +
                        "(or A B (notC))\n" +
                        "(or A (notB) C)\n" +
                        "(or (notA) B C)\n";
        final List<String> literals2 = List.of("A", "B", "C");
        final List<String> negatedLiterals2 = List.of("notA", "notB", "notC");
        System.out.println(SatSolverZ.isSatisfiable(example2, literals2, negatedLiterals2));

        final String example3 =
                "(and\n" +
                        "(or A B)\n" +
                        "(or A (notB))\n" +
                        "(or (notA) (notB))\n";
        final List<String> literals3 = List.of("A", "B");
        final List<String> negatedLiterals3 = List.of("notA", "notB");
        System.out.println(SatSolverZ.isSatisfiable(example3, literals3, negatedLiterals3));

        final String example4 =
                "(and\n" +
                        "(or A B)\n" +
                        "(or A (notB))\n" +
                        "(or (notA) B)\n" +
                        "(or (notA) (notB))\n";
        final List<String> literals4 = List.of("A", "B");
        final List<String> negatedLiterals4 = List.of("notA", "notB");
        System.out.println(SatSolverZ.isSatisfiable(example4, literals4, negatedLiterals4));
    }
}