package streams.models;

import java.util.Objects;

public class City {
    private final int id;
    private final String name;
    private final int population;
    private final String country;

    public City(int id, String name, int population, String country) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.country = country;
    }

    /**
     * Parses a line from the Cities.csv file into a City object.
     *
     * @param line a line from the Cities.csv file
     * @return a City object
     */
    public static City fromString(String line) {
        String[] parts = line.split(",");
        return new City(Integer.parseInt(parts[0]),
                parts[1],
                Integer.parseInt(parts[2]),
                parts[3]);
    }


    //setters, getters, equals, hashCode, and toString
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && population == city.population && name.equals(city.name) && country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, population);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                '}';
    }
}


