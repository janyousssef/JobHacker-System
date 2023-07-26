package streams.models;

import java.util.Objects;

public class Country {
    private final String countryCode;
    private final String name;
    private final String continent;
    private final double surfaceArea;
    private final double population;
    private final double gnp;
    private final int capital;


    public Country(String code, String name, String continent, double surfaceArea, double population, double gnp, int capital) {
        this.countryCode = code;
        this.name = name;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.population = population;
        this.gnp = gnp;
        this.capital = capital;
    }

    /**
     * Parses a line from the Countries.csv file into a Country object.
     *
     * @param line a line from the Countries.csv file
     * @return a Country object
     */
    public static Country fromString(String line) {
        String[] parts = line.split(",");
        return new Country(parts[0],
                parts[1],
                parts[2],
                Double.parseDouble(parts[3]),
                Double.parseDouble(parts[4]),
                Double.parseDouble(parts[5]),
                Integer.parseInt(parts[6]));
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public double getPopulation() {
        return population;
    }

    public double getGnp() {
        return gnp;
    }

    public int getCapital() {
        return capital;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Double.compare(country.surfaceArea,
                surfaceArea) == 0 && population == country.population && Double.compare(country.gnp,
                gnp) == 0 && capital == country.capital && Objects.equals(countryCode, country.countryCode) && Objects.equals(name,
                country.name) && Objects.equals(continent, country.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, name, continent, surfaceArea, population, gnp, capital);

    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + countryCode + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", population=" + population +
                ", gnp=" + gnp +
                ", capital=" + capital +
                '}';
    }

}
