
//package bahlwan.library.homework.controllers;
//
//import graphql.ExecutionResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/graphql**")
//public class GraphQLController {
//
//    @PostMapping("/graphql.authors")
//    public ResponseEntity<Object> getAllAuthors(@RequestBody String query) {
//        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
//        return new ResponseEntity<>(execute, HttpStatus.OK);
//    }
//}
