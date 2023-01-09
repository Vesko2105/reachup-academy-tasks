package tasks.map;

public class RouteFinderTest {
    public static void main(String[] args) {
        RouteFinder routeFinder = new RouteFinder("./src/main/resources/Bulgarian Roads.csv", "Vidin", "Burgas");
        routeFinder.findPath();
    }
}
