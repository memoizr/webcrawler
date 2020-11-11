package core;

import models.Library;

import java.util.stream.Stream;

public interface LibFinder {
    // Instead of Stream, maybe an Rx Observable or a Kotlin Flow would make me happier.
    // Not a huge fan of Future either, not as composable as other alternatives.
    // All this stuff would work so much better in a language that supports higher-kinded-types
    // like Scala. That way you can abstract over any of these Monads and compose them
    // much more gracefully.
    Stream<Library> findLibraries(String url);
}
