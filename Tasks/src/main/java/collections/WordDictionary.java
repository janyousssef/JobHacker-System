package collections;

import java.util.*;
import java.util.stream.Collectors;

public class WordDictionary {
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
    private static final Map<Character, Set<String>> map = createMap();



    public void printAll() {
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    public void printForChar(Character c) {
        System.out.println(c + " -> " + map.getOrDefault(c,
                Collections.singleton("There are no words for this letter")));

    }

    private static Map<Character, Set<String>> createMap() {
        return strings.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0), TreeMap::new, Collectors.toCollection(TreeSet::new)));
    }

    public void addWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word to add");
        String word = scanner.nextLine();
        addWordInternal(word);
        printForChar(word.charAt(0));
    }

    private static void addWordInternal(String word) {
        if (map.containsKey(word.charAt(0))) {
            map.get(word.charAt(0)).add(word);
        } else {
            map.put(word.charAt(0), new TreeSet<>(Collections.singletonList(word)));
        }
    }
}