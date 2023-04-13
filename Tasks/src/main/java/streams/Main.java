package streams;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> cities = Repository.getAsList("/Cities.csv");
        List<String> countries = Repository.getAsList("/Countries.csv");

        List<Country> cityList = countries.stream()
                .map(Country::fromString)
                .collect(Collectors.toList());
        cityList.forEach(System.out::println);
    }

}