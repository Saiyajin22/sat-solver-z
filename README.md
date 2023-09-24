# sat-solver-z
Simple SAT Solver implemented in Java.

It can solve SAT problem only in CNF Form.

# How to use it
## Step1
Create a CNF formula like this one:  
final String formula =  "(and\n" +  
"(or A B)\n" +  
"(or A (notB))\n" +  
"(or (notA) (notB))\n";

The "and" means, that every clause after it will get conjucted. The "or" means, that every literal after it will get disjuncted inside that clause. So E.g: (or A B) equals to the following: **A or B**. The "\n" at every line's end is the spliterator character, which the SAT Solver highly depends on. Now you should have the idea, how to create CNF formulas for the solver. You can also check the test class which contains a few more examples.

## Step2
Define the literals as a List<String>, like this one: **final List<String> literals = List.of("A", "B");**

## Step3
Define the negation of the literals as a List<String>, like this one: **final List<String> negatedLiterals = List.of("notA", "notB");**

Step2 and Step3 is needed for the algorithm, now you can get it, why is it only a Simple Solver, because it depends heavily on the user input, It's not implemented automatically detect literals based on the formula, that's why you need to pass them to the solver Lists.

## Step4
Call the solver function to retrieve the result.
E.g: boolean result = SatSolverZ.isSatisfiable(formula, literals, negatedLiterals);
The method is static, so you can directly call it, and returns true, if the formula is satisfiable, or false if not.

# P.S:
It would be nice to have a program with a GUI with this, and to be able to input only the CNF formula itself (or insert any formula because the solver compiles it to CNF form), but my goal was only the have the concept of a Solver, not to create a perfect one, because there  are already many SAT Solvers.

