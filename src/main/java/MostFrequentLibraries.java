import core.LibFinder;
import core.SearchEngine;
import models.Library;
import models.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MostFrequentLibraries {
    private final SearchEngine searchEngine;
    private final LibFinder libFinder;
    private final int limit = 5;

    public MostFrequentLibraries(final SearchEngine searchEngine, final LibFinder libFinder) {
        this.searchEngine = searchEngine;
        this.libFinder = libFinder;
    }

    public List<Library> fromTerm(final String query) {
        final Stream<SearchResult> results = searchEngine.searchFor(query);
        final HashMap<Library, Integer> popularLibraries = new HashMap<>();

        // Requests in parallel, saves time! If I was to use libraries, I'd use Rx for something
        // more complicated. Streams is a bit of an awkward API in my opinion though it has reasons to
        // be the way it is.
        final List<Library> libs = results.parallel()
                .flatMap(result -> libFinder.findLibraries(result.getLink()))
                .collect(Collectors.toList());

        // There's practical limits to how the same library with different names can be handled.
        // At least, with some string processing /regex it could be possible to handle different versions of
        // otherwise similarly named libraries.
        // With WebDriver and some JS magic maybe more info could be obtained. I'm not sure there'd be ONE trivial
        // solution to this.
        libs.forEach((library) -> popularLibraries.put(library, popularLibraries.getOrDefault(library, 0) + 1));

        return popularLibraries.entrySet()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
