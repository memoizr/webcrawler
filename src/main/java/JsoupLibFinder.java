import core.LibFinder;
import models.Library;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.CommonHeaders;

import java.util.stream.Stream;

// No this isn't good enough. It only looks as the scripts embedded in the HTML, and it
// completely ignores async libraries. For a real web crawler something like Selenium, WebDriver
// would work better. I started playing with it but then that'd be an extra lib, and I'd rather
// have Jsoup and a sub-optimal solution, for the sake of time constraints.
public class JsoupLibFinder implements LibFinder {
    @Override
    public Stream<Library> findLibraries(String url) {
        // Avoid unnecessary use of checked exceptions: Effective Java Item 71 - Joshua Bolsh
        // No, ignore Item 70
        Stream<Library> result = null;
        try {
            // Jsoup is so nice
             Document doc = Jsoup.connect(url)
                    .headers(CommonHeaders.headers)
                    .get();

            result =  doc
                    .select("script")
                    .stream()
                    .map(element -> element.attr("src"))
                    .filter(element -> element != null && !element.isEmpty() && element.contains("js"))
                    .map(link -> {
                        // Makes me sad but it's just for the sake of getting the test done in time
                        String[] list = link.split("/");
                        return list[list.length -1];
                    })
                    .map(Library::new);

            // ugly
        } catch (Exception e) {
            // ugly
            e.printStackTrace();
        }

        // ugly
        return result;
    }
}
