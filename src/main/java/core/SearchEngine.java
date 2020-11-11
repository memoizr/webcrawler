package core;

import models.SearchResult;

import java.util.stream.Stream;

public interface SearchEngine {
    // See rant in LibFinder
    Stream<SearchResult> searchFor(String query);
}
