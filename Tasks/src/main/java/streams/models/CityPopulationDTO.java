package streams.models;

/**
 * A DTO that represents a city and its population.
 */
public class CityPopulationDTO {
    private final String name;
    private final int population;
    private final String country;


    public CityPopulationDTO(City city) {
        this.name = city.getName();
        this.population = city.getPopulation();
        this.country = city.getCountry();
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "CityPopulation{" +
                "name='" + name + '\'' +
                ", population=" + population +
                '}';
    }

}
