package streams.runners;

import streams.Repository;
import streams.models.City;
import streams.models.CityPopulationDTO;
import streams.models.Country;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectiveOneRunner {
    private static final Set<City> cities = Repository.getCities();
    private static final Set<Country> countries = Repository.getCountries();
    private static final Map<String, Country> codeToCountryMap = countries.stream()
            .collect(Collectors.toMap(Country::getCountryCode, c -> c));
    private static final Comparator<? super CityPopulationDTO> populationComparator = Comparator.comparing(
            CityPopulationDTO::getPopulation);

    public static void run() {

        Map<String, Optional<CityPopulationDTO>> citiesByCountry = getBiggestCityForEachCountry();

        printResults(citiesByCountry);
    }

    private static Map<String, Optional<CityPopulationDTO>> getBiggestCityForEachCountry() {
        return cities.stream()
                .map(CityPopulationDTO::new)
                .collect(
                        Collectors.groupingBy(CityPopulationDTO::getCountry,
                                Collectors.maxBy(populationComparator)));
    }

    private static void printResults(Map<String, Optional<CityPopulationDTO>> citiesByCountry) {
        citiesByCountry.entrySet()
                .forEach(ObjectiveOneRunner::printMaxPopulation);
    }


    private static void printMaxPopulation(Map.Entry<String, Optional<CityPopulationDTO>> e) {
        //some country codes are not in the countries.csv file, this was causing a null pointer exception
        String countryName = codeToCountryMap.get(e.getKey()) == null ? "No Country with Such Code" : codeToCountryMap.get(
                        e.getKey())
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