package bahlwan.library.homework.graphql;

import java.awt.print.Pageable;

public class PaginationFilter {

    private int page;
    private int size;

    public PaginationFilter() {
        this.page = 0;
        this.size = 100;
    }

    public PaginationFilter(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
