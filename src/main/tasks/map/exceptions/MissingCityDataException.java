package tasks.map.exceptions;

public class MissingCityDataException extends RuntimeException {
    String city;
    String otherCity;

    public MissingCityDataException(String city, String otherCity) {
        this.city = city;
        this.otherCity = otherCity;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getMessage(String city, String otherCityName) {
        return String.format("City object %s is missing information about %s", city, otherCityName);
    }
}
