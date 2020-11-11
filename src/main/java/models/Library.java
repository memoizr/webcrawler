package models;

//
// Kotlin: data class Library(val name: String)
// Java:
public class Library {
    private final String name;

    public Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;

        return name != null ? name.equals(library.name) : library.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "models.Library{" +
                "name='" + name + '\'' +
                '}';
    }
}
