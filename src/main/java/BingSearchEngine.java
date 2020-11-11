import core.SearchEngine;
import models.SearchResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.CommonHeaders;

import java.io.IOException;
import java.util.stream.Stream;

public class BingSearchEngine implements SearchEngine {
    // Hardcoded because why not. It's specific to Bing.
    private final String baseUrl = "https://www.bing.com/search?q=";

    @Override
    public Stream<SearchResult> searchFor(String query) {
        // Checked exceptions make me sad. There are many ways of
        // treating them better especially with Kotlin.
        Document doc = null;
        try {
            // Yes Jsoup. Ain't nobody got time for anything else.
            doc = Jsoup.connect(baseUrl + query).headers(CommonHeaders.headers).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc.select("body")
                .select(".b_algo")
                .stream()
                .map(element -> element.select("a").attr("href"))
                .map(SearchResult::new);
    }
}
