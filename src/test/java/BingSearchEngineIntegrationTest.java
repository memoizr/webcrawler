import core.SearchEngine;
import models.SearchResult;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BingSearchEngineIntegrationTest {
    // I don't bother with visibility modifiers in tests, these classes are not used as objects anyway
    SearchEngine searchEngine = new BingSearchEngine();

    @Test
    public void parsesSearchResults() {
        List<SearchResult> results = searchEngine.searchFor("gutenberg").collect(Collectors.toList());
        assertEquals(10, results.size());
        assertTrue(results.get(0).getLink().startsWith("http"));
    }
}
