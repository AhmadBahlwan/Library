package bahlwan.library.homework.graphql;


public class BookFilter {

    private FilterField title;
    private FilterField description;
    private FilterField publishDate;


    public BookFilter() {
    }

    public BookFilter(FilterField title, FilterField description, FilterField publishDate) {
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
    }

    public FilterField getTitle() {
        return title;
    }

    public void setTitle(FilterField title) {
        this.title = title;
    }

    public FilterField getDescription() {
        return description;
    }

    public void setDescription(FilterField description) {
        this.description = description;
    }

    public FilterField getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(FilterField publishDate) {
        this.publishDate = publishDate;
    }
}
