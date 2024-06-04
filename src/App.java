import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        NewtonPolynomial np = new NewtonPolynomial();
        String input = "";
        String[] terms = null;
        System.out.println("CURRENTLY IN INTERACTIVE SESSION");
        do {
            System.out.print("> ");
            input = in.nextLine().toLowerCase();
            terms = input.split("\s+");
            switch (terms[0]) {
                case "print":
                    if (terms.length != 1) {
                        System.err.println("Usage: print");
                        continue;
                    }
                    if (np.isEmpty()) continue;
                    System.out.println(np);
                    break;
                case "eval":
                    if (terms.length != 2) {
                        System.err.println("Usage: eval <x-value-to-evaluate>");
                        continue;
                    }
                    Double val = safeParseDouble(terms[1]);
                    if (val == null) {
                        System.err.println("[ERROR] Value for evaluation must be numeric");
                        continue;
                    }
                    if (np.isEmpty()) {
                        System.err.println("[ERROR] The polynomial is empty");
                        continue;
                    }
                    System.out.printf("p(%f) = %f\n", val, np.evaluate(val));
                    break;
                case "add":
                    if (terms.length != 3) {
                        System.err.println("Usage: add <x-coordinate-of-point> <y-coordinate-of-point");
                        continue;
                    }
                    Double x = safeParseDouble(terms[1]);
                    Double y = safeParseDouble(terms[2]);
                    if (x == null || y == null) {
                        System.err.println("[ERROR] Both x and y coordinates must be numeric");
                        continue;
                    }
                    np.addPoint(new Point(x, y));
                    break;
                case "clear":
                    if (terms.length != 1) {
                        System.err.println("Usage: clear");
                        continue;
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("CURRENTLY IN INTERACTIVE SESSION");
                    break;
                case "delete":
                    if (terms.length != 1) {
                        System.err.println("Usage: delete");
                        continue;
                    }
                    np = new NewtonPolynomial();
                    break;
                case "empty":
                    if (terms.length != 1) {
                        System.err.println("Usage: empty");
                        continue;
                    }
                    System.out.println(np.isEmpty());
                    break;
                default:
                    System.err.printf("Invalid command: '%s'\n", terms[0]);
                    break;
            }
        } while (!input.equals("exit"));

        in.close();
    }

    public static Double safeParseDouble(String val) {
        try {
            return Double.parseDouble(val);    
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
}
