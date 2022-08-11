package bahlwan.library.homework.utils;


import bahlwan.library.homework.graphql.PaginationFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationUtility {

    private PaginationUtility(){

    }

    public static Pageable getPerfectPageable(PaginationFilter paginationFilter){
        return paginationFilter != null ? PageRequest.of(paginationFilter.getPage(), paginationFilter.getSize())
                : PageRequest.of(0,50);
    }
}
