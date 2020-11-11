package models;

// Beautiful eh? So succinct and to the point, why waste time writing `data class SearchResult(val link: String)`?
public class SearchResult {
    private final String link;

    public SearchResult(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    // My eyes go blblblblbfphfphf after this point. How about yours?
    // Is there a subtle bug hiding here? Really, is there 0_o?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult that = (SearchResult) o;

        return link != null ? link.equals(that.link) : that.link == null;
    }

    @Override
    public int hashCode() {
        return link != null ? link.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "models.SearchResult{" +
                "link='" + link + '\'' +
                '}';
    }
}
