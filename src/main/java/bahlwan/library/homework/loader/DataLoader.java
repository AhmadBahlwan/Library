package bahlwan.library.homework.loader;

import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.repositories.AuthorRepository;
import bahlwan.library.homework.repositories.BookRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader {

    @Value("${datadump.books.location}")
    private String worksDumpLocation;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostConstruct
    public void startInitialization() {
        //initBooks();
    }

    private void initBooks() {
        Path path = Paths.get(worksDumpLocation);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String jsonString = line.substring(line.indexOf("{"));
                try {
                    Book book = new Book();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    setBookInfo(book, jsonObject);
                    setBookAuthors(book, jsonObject);
//                    JSONArray coverJsonArray = jsonObject.optJSONArray("covers");
//                    if (coverJsonArray !=null){
//                        List<String> coversIds = new ArrayList<>();
//                        for (int i=0; i<coverJsonArray.length();i++){
//                            coversIds.add(coverJsonArray.getString(i));
//                        }
//                        book.setCoverIds(coversIds);
//
//                    }
                    bookRepository.save(book);
                    //System.out.println(book.getTitle() + "saved.....");
                } catch (Exception e) {

                }
            });
        } catch (IOException e) {

        }
    }

    private void setBookInfo(Book book, JSONObject jsonObject) throws JSONException {
        book.setId(jsonObject.getString("key").replace("/works/", ""));
        book.setTitle(jsonObject.optString("title"));
        JSONObject descJsonObject = jsonObject.optJSONObject("description");
        if (descJsonObject != null) {
            book.setDescription(descJsonObject.optString("value"));
        }
        JSONObject publishedObj = jsonObject.optJSONObject("created");
        if (publishedObj != null) {
            String dateStr = publishedObj.optString("value");
            DateTimeFormatter dateFormat;
            try {
                dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
                book.setPublishDate(LocalDate.parse(dateStr, dateFormat));
            } catch (DateTimeParseException ex) {
                //dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            }
        }
    }

    private void setBookAuthors(Book book, JSONObject jsonObject) throws JSONException {
        JSONArray authorsJsonArray = jsonObject.optJSONArray("authors");
        if (authorsJsonArray != null) {
            List<String> authorsIds = new ArrayList<>();
            for (int i = 0; i < authorsJsonArray.length(); i++) {
                String authorId = authorsJsonArray.getJSONObject(i).getJSONObject("author").getString("key")
                        .replace("/authors/", "");

                authorsIds.add(authorId);
            }
            book.setAuthors(authorRepository.findAllById(authorsIds).stream().collect(Collectors.toSet()));
                        /*List<String>authorNames = authorsIds.stream().map(id->authorRepository.findById(id))
                                .map(optionalAuthor->{
                                    if (!optionalAuthor.isPresent()) return "Unknown Author";
                                    return optionalAuthor.get().getName();
                                }).collect(Collectors.toList());*/
        }
    }



    /*private void initAuthors(){
		Path path = Paths.get(authorDumpLocation);
		try(Stream<String> lines  = Files.lines(path)){
			lines.forEach(line->{
				String jsonString = line.substring(line.indexOf("{"));
				try{
					JSONObject jsonObject = new JSONObject(jsonString);

					Author author = new Author();
					author.setName(jsonObject.optString("name"));
					author.setPersonalName(jsonObject.optString("personal_name"));
					author.setId(jsonObject.optString("key").replace("/authors/",""));
					authorRepository.save(author);
					System.out.println(author.getName() + "saved.....");
				}catch (JSONException e){

				}

			});

		}catch (IOException e){

		}
	}*/
}
