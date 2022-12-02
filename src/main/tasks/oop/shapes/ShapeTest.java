package tasks.oop.shapes;

public class ShapeTest {
    public static void main(String[] args) {
        Circle circle = new Circle(0, 0, 10);
        //Rectangle rectangle = new Rectangle(0, 0, 10, 6);
        circle.draw(Color.BLUE, Color.RED);
//        //rectangle.draw(Color.BLUE, Color.PURPLE);
//        Triangle triangle = new Triangle(new Point(0, 0), new Point(0, 21), new Point(55, 0));
//        triangle.draw(Color.PURPLE, Color.YELLOW);
//        Point point = new Point(4.1, 2.28);
//        System.out.printf("Side A: %.2f%n", triangle.getSideA());
//        System.out.printf("Side B: %.2f%n", triangle.getSideB());
//        System.out.printf("Side C: %.2f%n", triangle.getSideC());
//        System.out.printf("Area: %.2f%n", triangle.area());
//        System.out.printf("Point P%s %s inside the triangle", point, triangle.containsPoint(point) ? "is" : "is not");
    }
}
