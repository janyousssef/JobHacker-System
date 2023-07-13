package streams2;

import streams2.domain.Product;
import streams2.domain.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class TestCases {
    private static final List<Product> products = new ProductRepository().addDummyData();

//    static {
//        products =
//    }

    static void allBooks() {
        System.out.println("Products belonging to category Books:");
        products.stream()
                .filter(belongsTo("Books"))
                .forEach(System.out::println);
    }

    public static void allBooksWithPriceAboveHundred() {
        System.out.println("\nProducts belonging to category Books and price above 100:");
        products.stream()
                .filter(belongsTo("Books"))
                .filter(p -> p.getPrice() > 100)
                .forEach(System.out::println);
    }


    public static void discountedToys() {
        System.out.println("\nAll Toys with a 10% discount:");
        products.stream()
                .filter(belongsTo("Toys"))
                .map(p -> p.withPrice(p.getPrice() * 0.9))
                .forEach(System.out::println);
    }

    public static void cheapestBooks(int n) {
        System.out.println("\nThe "+n+" cheapest Books:");
        products.stream()
                .filter(belongsTo("Books"))
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .limit(n)
                .forEach(System.out::println);
    }

    private static Predicate<Product> belongsTo(String category) {
        return p -> p.getCategory().equals(category);
    }
}
