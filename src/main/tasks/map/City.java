package tasks.map;

import tasks.map.exceptions.MissingCityDataException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class City {
    public final String name;
    public final Map<String, List<Integer>> stats;

    public City(String name, Map<String, List<Integer>> stats) {
        this.name = name;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public int getRoadLength(String key) {
        try {
            return stats.get(key).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCityDataException(name, key);
        }
    }

    public int getTravelingTime(String key) {
        try {
            return stats.get(key).get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCityDataException(name, key);
        }
    }

    public int getDistanceByAir(String key) {
        try {
            return stats.get(key).get(2);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCityDataException(name, key);
        }
    }

    public Map<String, List<Integer>> getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("City: %s | Cities info: %s", name, stats);
    }
}
