import models.Library;

import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        System.out.println("Type the query, then press enter");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(Pattern.compile("[\\r\\n;]+"));
        String arg = scanner.next();
        scanner.close();

        new MostFrequentLibraries(new BingSearchEngine(), new JsoupLibFinder())
                .fromTerm(arg)
                .stream()
                .map(Library::getName)
                .forEach(System.out::println);
    }
}
