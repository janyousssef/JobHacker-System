package collections;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String[] hundredWords =
            {"command", "practice", "distort", "linear", "hostage", "prosecute", "composer", "image", "extort", "cook",
                    "climate", "agriculture", "quiet", "district", "exception", "overview", "social", "wake", "copy",
                    "conclusion", "offer", "classroom", "dish", "colleague", "halt", "clerk", "courage", "maze",
                    "definite", "orbit", "dialect", "cereal", "generation", "compound", "college", "satellite", "pony",
                    "adviser", "rice", "please", "reason", "rumor", "brake", "parallel", "privacy", "form", "start",
                    "miss", "finish", "shave", "surface", "waiter", "reader", "transparent", "sailor", "essay",
                    "software", "reward", "throw", "remind", "star", "doll", "graze", "selection", "clique", "fantasy",
                    "repetition", "pastel", "ivory", "hay", "transform", "cream", "small", "damn", "view", "eternal",
                    "unique", "jurisdiction", "jewel", "education", "pitch", "railroad", "articulate", "introduction",
                    "slip", "fast", "command", "dash", "professor", "lobby", "exploit", "remember", "crusade", "ghost",
                    "forecast", "recommend", "financial", "argument", "favour", "ranch"};

    private static final ArrayList<String> strings = new ArrayList<>(Arrays.asList(hundredWords));

    private static final Map<Character, List<String>> map = createMap();

    public static void main(String[] args) {
        while(true){
            System.out.println("Enter 'all' to print all words, " +
                    "any letter to print the words for this letter only" +
                    " and, 'x' to exit");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "all":
                    printAll(map);
                    break;
                case "x":
                    return;
                default:
                    printForChar(choice.charAt(0));
            }
        }

    }

    private static void printAll(Map<Character, List<String>> map) {
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    private static void printForChar(Character c) {
        System.out.println(c + " -> " + map.getOrDefault(c,
                Collections.singletonList("There are no words for this letter")));

    }

    private static Map<Character, List<String>> createMap() {
        return strings.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0),
                                               Collectors.collectingAndThen(Collectors.toList(),
                                                                            list -> list.stream()
                                                                                    .sorted()
                                                                                    .collect(Collectors.toList()))));
    }
}
