public class Test {
    public static void main(String[] args) {
        Point[] points = {new Point(2,95), new Point(4,135), new Point(6,190), new Point(8,265), new Point(10,385), new Point(12,516)}; // initial points
        NewtonPolynomial np = new NewtonPolynomial(points);
        System.out.println(np);
        System.out.printf("p(9.2) = %.2f\n", np.evaluate(9.2));
        System.out.printf("p(15) = %.2f\n", np.evaluate(15));
    }
}