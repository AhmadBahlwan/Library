package bahlwan.library.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
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
	}

	private void initWorks() {
		Path path = Paths.get("C:\\Users\\Ahmad\\Desktop\\test-works.txt");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> {
				String jsonString = line.substring(line.indexOf("{"));
				try {
					JSONObject jsonObject = new JSONObject(jsonString);
					Book book = new Book();
					book.setId(Long.parseLong(jsonObject.getString("key").replace("/works/","")));
					book.setTitle(jsonObject.optString("title"));
					JSONObject descJsonObject = jsonObject.optJSONObject("description");
					if (descJsonObject!=null){
						book.setDescription(descJsonObject.optString("value"));
					}
					JSONObject publishedObj = jsonObject.optJSONObject("created");
					if (publishedObj!=null){
						String dateStr = publishedObj.optString("value");
						book.setPublishDate(LocalDate.parse(dateStr,dateFormat));
					}

					JSONArray authorsJsonArray = jsonObject.optJSONArray("authors");
					if (authorsJsonArray !=null){
						List<String> authorsIds = new ArrayList<>();
						for (int i=0;i<authorsJsonArray.length();i++){
							String authorId = authorsJsonArray.getJSONObject(i).getJSONObject("author").getString("key")
									.replace("/authors/","");

							authorsIds.add(authorId);
						}
						book.setAuthors(authorsIds);
						List<String>authorNames = authorsIds.stream().map(id->authorRepository.findById(id))
								.map(optionalAuthor->{
									if (!optionalAuthor.isPresent()) return "Unknown Author";
									return optionalAuthor.get().getName();
								}).collect(Collectors.toList());
						book.setAuthorNames(authorNames);
					}
					bookRepository.save(book);
					System.out.println(book.getName() + "saved.....");
				} catch (JSONException e) {

				}
			});
		}catch (IOException e){

		}
	}*/

}
