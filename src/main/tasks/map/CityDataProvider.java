package tasks.map;

import tasks.csv_reader.CSVReader;

import java.io.FileNotFoundException;
import java.util.*;

public class CityDataProvider {

    CSVReader csvReader;
    List<City> cities;

    public CityDataProvider(String filePath) {
        try {
            this.csvReader = new CSVReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
        this.cities = new ArrayList<>();
        parseCitiesFile();
    }

    private void parseCitiesFile() {
        List<List<String>> cityData = csvReader.read(0, csvReader.getRecordsCount());
        for (int i = 0; i < cityData.size(); i++) {
            String currentName = csvReader.getHeaderColumn().get(i);
            Map<String, List<Integer>> currentCityData = new HashMap<>();
            csvReader.get(i).forEach((cityName, cityInfo) -> {
                List<Integer> cityStats = Arrays.stream(cityInfo.split("\\|")).map(Integer::parseInt).toList();
                currentCityData.put(cityName, cityStats);
            });
            cities.add(new City(currentName, currentCityData));
        }
    }

    public City getCity(String key) {
        return cities.stream().filter(city -> city.name.equals(key)).findFirst().orElse(null);
    }

}
