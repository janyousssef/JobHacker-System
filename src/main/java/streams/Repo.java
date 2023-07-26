package streams;

import streams.models.City;
import streams.models.Country;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Repo {
    private static final String CURRENT_PATH = Paths.get("")
            .toAbsolutePath() + "/Tasks/src/main/java/streams/resources";
    private static final Set<City> cities = getAsList("/Cities.csv").stream()
            .map(City::fromString)
            .collect(Collectors.toSet());
    private static final Set<Country> countries = getAsList("/Countries.csv").stream()
            .map(Country::fromString)
            .collect(Collectors.toSet());

    public static Set<City> getCities() {
        return cities;
    }

    public static Set<Country> getCountries() {
        return countries;
    }


    /**
     * Reads a file from the resources folder and returns a list of lines.
     * removes any leading or trailing whitespace and replaces any ", " with "," for easier parsing.
     * absorbs any IOExceptions to separate error handling from business logic.
     * @param file the file to read
     * @return a list of lines
     */
    private static List<String> getAsList(String file) {
        try {
            return Files.readAllLines(Paths.get(CURRENT_PATH, file))
                    .stream()
                    .map(String::trim)
                    .map(l -> l.replace(", ", ","))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
