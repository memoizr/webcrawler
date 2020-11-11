import core.LibFinder;
import core.SearchEngine;
import models.Library;
import models.SearchResult;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class MostFrequentLibrariesTest {
    MockLibFinder libFinder = new MockLibFinder();
    MockSearchEngine searchEngine = new MockSearchEngine();

    static String wikipedia = "https://en.wikipedia.com";
    static String youtube = "https://youtube.com";
    static String nypost = "https://nypost.com";
    static String nytimes = "https://nytimes.com";
    static String guardian = "https://guardian.co.uk";
    static String sun = "https://sun.co.uk";
    static String news24 = "https://news24.com";
    static String ilsole = "https://ilsole.it";

    @Test
    public void whenThereAreHitsExtractsResults() {
        final var libraries = new MostFrequentLibraries(searchEngine, libFinder)
                .fromTerm("gutenberg");

        assertEquals(valueOf(1), searchEngine.searchForCallCount.get("gutenberg"));
        assertEquals(asList(
                new Library("jQuery"),
                new Library("GWT"),
                new Library("react"),
                new Library("1account"),
                new Library("bootstrap")
        ), libraries);
    }

    @Test
    public void whenThereAreNoHitsExtractsNoResults() {
        final var libraries = new MostFrequentLibraries(searchEngine, libFinder)
                .fromTerm("obscure");

        assertEquals(valueOf(1), searchEngine.searchForCallCount.get("obscure"));
        assertEquals(emptyList(), libraries);
    }

    // These are old school fakes, in real life I use Mockito, Mockk etc as it's so much boilerplate!
    static class MockSearchEngine implements SearchEngine {
        final HashMap<String, Integer> searchForCallCount = new HashMap<>();

        @Override
        public Stream<SearchResult> searchFor(String query) {
            searchForCallCount.put(query, searchForCallCount.getOrDefault(query, 0) + 1);
            // With a mocking library this would not look so horrible.
            // In fact it could look better even without a library but not today
            if (query.equals("obscure")) {
                return Stream.empty();
            } else {
                return Stream.of(
                        new SearchResult(wikipedia),
                        new SearchResult(youtube),
                        new SearchResult(nypost),
                        new SearchResult(nytimes),
                        new SearchResult(guardian),
                        new SearchResult(sun),
                        new SearchResult(news24),
                        new SearchResult(ilsole)
                );
            }
        }
    }

    class MockLibFinder implements LibFinder {

        HashMap<String, List<Library>> libraries = new HashMap<>();

        MockLibFinder() {
            libraries.put(wikipedia, asList(new Library("jQuery")));
            libraries.put(youtube, asList(new Library("jQuery"), new Library("1account")));
            libraries.put(nypost, asList(new Library("jQuery"), new Library("GWT")));
            libraries.put(nytimes, asList(new Library("jQuery"), new Library("GWT")));
            libraries.put(guardian, asList(new Library("jQuery"), new Library("GWT")));
            libraries.put(sun, asList(new Library("react"), new Library("GWT")));
            libraries.put(news24, asList(new Library("react"), new Library("1account")));
            libraries.put(ilsole, asList(new Library("react"), new Library("bootstrap")));
        }

        @Override
        public Stream<Library> findLibraries(String url) {
            return libraries.get(url).stream();
        }
    }
}
