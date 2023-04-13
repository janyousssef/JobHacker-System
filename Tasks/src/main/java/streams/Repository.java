package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private static final String CURRENT_PATH = Paths.get("")
            .toAbsolutePath() + "/Tasks/src/main/java/streams/resources";

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


    public static List<City> getCities() {
        return getAsList("/Cities.csv").stream()
                .map(City::fromString)
                .collect(Collectors.toList());
    }

    public static List<Country> getCountries() {
        return getAsList("/Countries.csv").stream()
                .map(Country::fromString)
                .collect(Collectors.toList());
    }
}
