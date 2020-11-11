
import core.LibFinder;
import core.SearchEngine;
import models.Library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostFrequentLibraries {
    private final SearchEngine searchEngine;
    private final LibFinder libFinder;
    private final int limit = 5;

    public MostFrequentLibraries(final SearchEngine searchEngine, final LibFinder libFinder) {
        this.searchEngine = searchEngine;
        this.libFinder = libFinder;
    }

    public List<Library> fromTerm(final String query) {
        final var results = searchEngine.searchFor(query);
        final var popularLibraries = new HashMap<Library, Integer>();

        // Requests in parallel, saves time! If I was to use libraries, I'd use Rx for something
        // more complicated. Streams is a bit of an awkward API in my opinion though it has reasons to
        // be the way it is.
        final var libs = results.parallel()
                .flatMap(result -> libFinder.findLibraries(result.getLink()))
                .collect(Collectors.toList());

        libs.forEach((library) -> popularLibraries.put(library, popularLibraries.getOrDefault(library, 0) + 1));

        return popularLibraries.entrySet()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
