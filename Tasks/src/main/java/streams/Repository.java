package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    static final String CURRENT_PATH = Paths.get("")
            .toAbsolutePath() + "/Tasks/src/main/java/streams/resources";

    static List<String> getAsList(String x) throws IOException {
        return Files.readAllLines(Paths.get(CURRENT_PATH, x))
                .stream()
                .map(String::trim)

                .map(l -> l.replace(", ", ","))
                .collect(Collectors.toList());
    }

}