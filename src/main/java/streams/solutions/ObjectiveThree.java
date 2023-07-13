package streams.solutions;

import streams.Repo;
import streams.models.City;
import streams.models.Country;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class ObjectiveThree {
    private static final Set<Country> countries = Repo.getCountries();
    private static final Set<City> cities = Repo.getCities();
    private static final List<Integer> capitalsIds = countries.stream()
            .map(Country::getCapital)
            .collect(toList());
    private static final Comparator<? super City> populationComparator = Comparator.comparing(City::getPopulation);
    private static final City biggestCity = cities.stream()
            .filter(c -> isCapital(c.getId()))
            .max(populationComparator)
            .get();

    public static void solve() {
        printBiggestCity(biggestCity);
    }

    private static void printBiggestCity(City biggestCity) {
        System.out.println("The biggest capital city in the world is " + biggestCity.getName() + " with a population of " + biggestCity.getPopulation());
    }

    private static boolean isCapital(int cityId) {
        return capitalsIds.contains(cityId);
    }


}