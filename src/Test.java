public class Test {
    public static void main(String[] args) {
        Point[] points = {new Point(2,3), new Point(3,5), new Point(6,4)};
        NewtonInterpolation np = new NewtonInterpolation(points);
        System.out.println("Before adding new point:   " + np);
        np.addPoint(new Point(10,1));
        System.out.println("After adding point (10,1): " + np);
    }
}