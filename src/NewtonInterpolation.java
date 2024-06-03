import java.util.Arrays;

/**
 * Utility class for Newtonian interpolation using Newton's polynomial.
 * For visualizing the polynomial, simply print the object of this class.
 * @author Ricardo B. Süffert.
 */
public class NewtonInterpolation {
    private Point[] points; // the points currently considered for the interpolation
    private double[][] dividedDifferences; // the divided differences table/matrix

    /**
     * CONSTRUCTOR.
     * @param points the list of points to be taken into consideration in the initial Newton's polynomial.
     */
    public NewtonInterpolation(Point[] points) {
        this.points = points;
        this.dividedDifferences = new double[points.length][points.length];
        calculateDividedDifferences();
    }

    /**
     * Calculates the divided differences matrix, where the first row contains the coefficients of the Newton's polynomial.
     */
    private void calculateDividedDifferences() {
        // fill the first column with y values
        for (int i = 0; i < points.length; i++) {
            dividedDifferences[i][0] = points[i].y();
        }

        // calculate the divided differences for higher orders
        for (int j = 1; j < points.length; j++) {
            for (int i = 0; i < points.length - j; i++) {
                dividedDifferences[i][j] = (dividedDifferences[i + 1][j - 1] - dividedDifferences[i][j - 1]) / (points[i + j].x() - points[i].x());
            }
        }
    }

    /**
     * Adds a point to this Newton's polynomial.
     * @param p the point to be added.
     */
    public void addPoint(Point p) {
        // extend the points array
        Point[] newPoints = Arrays.copyOf(points, points.length+1);
        newPoints[points.length] = p;
        this.points = newPoints;

        // extend the divided differences matrix
        double[][] newDividedDifferences = new double[points.length][points.length];
        for (int i=0; i<this.dividedDifferences.length; i++)
            System.arraycopy(dividedDifferences[i], 0, newDividedDifferences[i], 0, dividedDifferences[i].length);
        this.dividedDifferences = newDividedDifferences;

        // calculating the new divided differences including the new point
        dividedDifferences[points.length - 1][0] = p.y(); // first column (y values)
        for (int j=1; j<points.length; j++) {
            for (int i=0; i<points.length-j; i++)
                dividedDifferences[i][j] = (dividedDifferences[i + 1][j - 1] - dividedDifferences[i][j - 1]) / (points[i + j].x() - points[i].x());
        }
    }

    /**
     * Evaluates the Newton's polynomial for a given {@code x}.
     * @param x the value for which the Newton's polynomial will be calculated.
     * @return the result of the polynomial for {@code x}; that is, {@code p(x)}.
     */
    public double evaluate(double x) {
        double result = dividedDifferences[0][0];
        
        for (int i=1; i<points.length; i++) {
            double term = dividedDifferences[0][i]; // coefficient
            for (int j=0; j<i; j++)
                term *= (x - points[j].x()); // multiply by (value - x_j)
            result += term; // add result of the term to the global result
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder polynomial = new StringBuilder();
        polynomial.append(dividedDifferences[0][0]);

        for (int i=1; i<points.length; i++) {
            if (dividedDifferences[0][i] != 0) {
                polynomial.append(" + ");
                polynomial.append(dividedDifferences[0][i]);
                for (int j=0; j<i; j++)
                    polynomial.append("(x - ").append(points[j].x()).append(")");
            }
        }

        return polynomial.toString();
    }
}