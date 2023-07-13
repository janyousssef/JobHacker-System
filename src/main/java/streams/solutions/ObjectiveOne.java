package streams.solutions;

import streams.Repo;
import streams.models.City;
import streams.models.CityPopulationDTO;
import streams.models.Country;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class ObjectiveOne {
    private static final Set<City> cities = Repo.getCities();
    private static final Set<Country> countries = Repo.getCountries();
    private static final Map<String, Country> codeToCountryMap = countries.stream()
            .collect(toMap(Country::getCountryCode, c -> c));
    private static final Comparator<? super CityPopulationDTO> populationComparator =
            Comparator.comparing(CityPopulationDTO::getPopulation);

    public static void solve() {

        Map<String, Optional<CityPopulationDTO>> citiesByCountry = getBiggestCityForEachCountry();

        printResults(citiesByCountry);
    }

    private static Map<String, Optional<CityPopulationDTO>> getBiggestCityForEachCountry() {
        return cities.stream()
                .map(CityPopulationDTO::new)
                .collect(groupingBy(CityPopulationDTO::getCountry, maxBy(populationComparator)));
    }

    private static void printResults(Map<String, Optional<CityPopulationDTO>> citiesByCountry) {
        citiesByCountry.entrySet()
                .forEach(ObjectiveOne::printMaxPopulation);
    }


    private static void printMaxPopulation(Map.Entry<String, Optional<CityPopulationDTO>> e) {
        //some country codes are not in the countries.csv file, this was causing a null pointer exception
        String countryName =
                codeToCountryMap.get(e.getKey()) == null ? "No Country with Such Code" : codeToCountryMap.get(e.getKey())
                        .getName();

        String cityName = e.getValue()
                .get()
                .getName();

        int cityPopulation = e.getValue()
                .get()
                .getPopulation();

        System.out.printf("The highest population city in %s is %s with %d people%n",
                countryName,
                cityName,
                cityPopulation);
    }
}