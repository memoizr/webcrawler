import models.Library;

import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        System.out.println("Enter query, then press enter");
        var scanner = new Scanner(System.in);
        scanner.useDelimiter(Pattern.compile("[\\r\\n;]+"));
        var arg = scanner.next();
        scanner.close();

        new MostFrequentLibraries(new BingSearchEngine(), new JsoupLibFinder())
                .fromTerm(arg)
                .stream()
                .map(Library::getName)
                // Good old println
                .forEach(System.out::println);
    }
}
