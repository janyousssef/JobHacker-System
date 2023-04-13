package streams;

import streams.models.City;
import streams.models.CityPopulationDTO;
import streams.models.Country;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectiveTwoRunner {
    private static final Set<City> cities = Repository.getCities();
    private static final Set<Country> countries = Repository.getCountries();

    static void run() {
        Map<String, List<CityPopulationDTO>> citiesByContinent = getCitiesByContinent();
        printResults(citiesByContinent);
    }


    private static Map<String, List<CityPopulationDTO>> getCitiesByContinent() {
        return countries.stream()
                .collect(Collectors.groupingBy(Country::getContinent,
                        //get a list of cities for each country in the continent
                        Collectors.mapping(country -> findCitiesFor(country),
                                Collectors.collectingAndThen(Collectors.toList(),
                                        listOfLists -> flatten(listOfLists)))));
    }


    private static List<CityPopulationDTO> findCitiesFor(Country country) {
        return cities.stream()
                .filter(city -> city.getCountryCode()
                        .equals(country.getCountryCode()))
                .map(CityPopulationDTO::new)
                .collect(Collectors.toList());
    }

    private static List<CityPopulationDTO> flatten(List<List<CityPopulationDTO>> listOfLists) {
        return listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static void printResults(Map<String, List<CityPopulationDTO>> citiesByContinent) {
        citiesByContinent.forEach((key, value) -> value
                .stream()
                .max(Comparator.comparing(CityPopulationDTO::getPopulation))
                .ifPresent(city -> printBiggestCity(key, city)));
         }

    private static void printBiggestCity(String continent, CityPopulationDTO city) {
        System.out.printf("The biggest city in %s is %s with %d people%n",
                continent,
                city.getName(),
                city.getPopulation());
    }


}