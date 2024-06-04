## Scope
Program that performs interpolation through the Newton's polynomial. This has been developed as the third and last assignment for the Numeric Methods class.

## Folder structure
- `bin`: where the compiled `.class` Java files are stored.
- `docs`: where the directions of the assignment and the final PDF report are stored.
- `src`: where the Java source-code is stored.

## Using the program
The main class is the `NewtonPolynomial` class. It contains some utility methods for performing interpolation using Newton's polynomial, and those methods are documented through Javadoc, so your IDE should probably have built-in IntelliSense support for helping you with using and reading the documentation of the methods of the class. If not, you may manually generate the Java API-style documentation through Javadoc or install an extension to your IDE. The `Test` class also provides a sample usage of the `NewtonPolynomial` utility class.

You may also run the `App` class to interactively build and evaluate your polynomials through CLI! These are the available commands:
- `add <x-coordinate-of-point> <y-coordinate-of-point>`: adds a point to the polynomial;
- `print`: prints the currently built polynomial;
- `eval <x-value-to-evaluate>`: evaluates `p(x)` using the current polynomial;
- `clear`: clears the console;
- `delete`: resests the polynomial by removing all points from it;
- `empty`: returns whether or not the polynomial is empty (that is, has no points in it);
- `exit`: terminates the interactive session.