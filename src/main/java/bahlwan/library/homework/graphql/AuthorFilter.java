package bahlwan.library.homework.graphql;

public class AuthorFilter {

    private FilterField name;
    private FilterField birthDate;

    public AuthorFilter() {
    }

    public AuthorFilter(FilterField name, FilterField birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public FilterField getName() {
        return name;
    }

    public void setName(FilterField name) {
        this.name = name;
    }

    public FilterField getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(FilterField birthDate) {
        this.birthDate = birthDate;
    }
}
