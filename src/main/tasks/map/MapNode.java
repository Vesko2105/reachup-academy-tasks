package tasks.map;

import java.util.LinkedList;
import java.util.List;

public class MapNode {
    private final City current;
    private final List<MapNode> visited;
    private static City destination;
    private final int totalTime;

    public MapNode(City current, City destination) {
        this.current = current;
        MapNode.destination = destination;
        this.totalTime = 0;
        this.visited = new LinkedList<>();
        visited.add(this);
    }

    public MapNode(City current, List<MapNode> visited, int timeToReach) {
        this.current = current;
        this.visited = visited;
        this.totalTime = visited.get(visited.size() - 1).totalTime + timeToReach;
        visited.add(this);
    }

    public List<MapNode> getChildren(CityDataProvider cityDataProvider) {
        List<MapNode> children = new LinkedList<>();
        current.getStats().forEach((currentCityName, currentCityStats) -> {
            if (isValid(currentCityName)) {
                children.add(new MapNode(
                        cityDataProvider.getCity(currentCityName),
                        new LinkedList<>(visited),
                        current.getTravelingTime(currentCityName)
                ));
            }
        });
        return children;
    }

    private boolean isValid(String city) {
        return current.getRoadLength(city) > -1 &&
                visited.stream().noneMatch(node -> node.current.name.equals(city));
    }

    public int evaluate() {
        return totalTime + current.getDistanceByAir(destination.getName());
    }

    public boolean isGoal() {
        return current.equals(destination);
    }

    public List<MapNode> getVisited() {
        return visited;
    }

    @Override
    public String toString() {
        return String.format("City: %s, Travel time so far: %d", current.name, totalTime);
    }
}
