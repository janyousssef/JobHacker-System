package collections;

import java.util.Scanner;

public class Main {
    static WordDictionary wordDictionary = new WordDictionary();

    public static void main(String[] args) {
        while (true) {
            promptForChoice();
        }


    }

    private static void promptForChoice() {
        System.out.println("Enter 'all' to print all words, " + "any letter to print the words for this letter only" + " and, 'exit' to exit");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice.toLowerCase()) {
            case "all":
                wordDictionary.printAll();
                break;
            case "add":
                wordDictionary.addWord();
                break;
            case "exit":
                break;
            default:
                wordDictionary.printForChar(choice.charAt(0));
        }
    }
}
