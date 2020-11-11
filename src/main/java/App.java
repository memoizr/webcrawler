import models.Library;

public class App {
    public static void main(String[] args) {
        // Supply the right number of args or else!! Seriously I'm not gonna write a help page
        // There are nice libraries for that like kotlinx-cli
        var arg = args[0];

        new MostFrequentLibraries(new BingSearchEngine(), new JsoupLibFinder())
                .fromTerm(arg)
                .stream()
                .map(Library::getName)
                // Good old println
                .forEach(System.out::println);
    }
}
