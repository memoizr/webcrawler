import core.LibFinder;
import models.Library;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Collectors;

// This test is flaky and a no-no in real life, though it helped me develop the feature.
// With more time I'd add a unit test, avoiding network requests
// when testing
public class LibFinderIntegrationTest {
    LibFinder libFinder = new JsoupLibFinder();

    @Test @Ignore
    public void parsesLibraries() {
        var libs = libFinder.findLibraries("https://jsoup.org").collect(Collectors.toList());

        Assert.assertEquals(2, libs.size());
        System.out.println(libs);
        Assert.assertTrue(libs.contains(new Library("prettify.js")));
    }
}
