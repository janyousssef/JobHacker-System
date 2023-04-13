package streams.runners;

import streams.Repo;
import streams.models.City;
import streams.models.CityPopulationDTO;
import streams.models.Country;

import java.util.*;
import java.util.stream.Collectors;

public class ObjectiveTwoRunner {
    private static final Set<City> cities = Repo.getCities();
    private static final Set<Country> countries = Repo.getCountries();
    private static final Comparator<? super CityPopulationDTO> populationComparator =
            Comparator.comparing(CityPopulationDTO::getPopulation);

    public static void run() {
        Map<String, List<CityPopulationDTO>> citiesByContinent = getCitiesByContinent();
        Map<String, Optional<CityPopulationDTO>> biggestCities = getBiggestCities(citiesByContinent);
        printBiggestCityForEachContinent(biggestCities);

    }


    private static Map<String, List<CityPopulationDTO>> getCitiesByContinent() {
        return countries.stream()
                .collect(Collectors.groupingBy(Country::getContinent,
                        //get a list of cities for each country in the continent
                        Collectors.mapping(country -> findCitiesFor(country),
                                //this collector allows us to apply a transformation to the collection,
                                //in this case we flatten the list of lists
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

    private static Map<String, Optional<CityPopulationDTO>> getBiggestCities(Map<String, List<CityPopulationDTO>> citiesByContinent) {
        return citiesByContinent.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .max(populationComparator)));
    }

    private static void printBiggestCityForEachContinent(Map<String, Optional<CityPopulationDTO>> biggestCities) {
        //some continents have no cities, this was causing a null pointer exception
        CityPopulationDTO UNKNOWN_CITY = new CityPopulationDTO(new City(0, "NO CITY FOUND", 0, "Unknown"));
        biggestCities.forEach((key, value) -> printBiggestCity(key, value.orElse(UNKNOWN_CITY)));
    }

    private static void printBiggestCity(String continent, CityPopulationDTO city) {
        System.out.printf("The biggest city in %s is %s with %d people%n",
                continent,
                city.getName(),
                city.getPopulation());
    }


}