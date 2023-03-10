package at.artmanns.catalogservice;

import at.artmanns.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {

        var book = Book.builder()
            .isbn("1234567891")
            .title("Northern Lights")
            .author("Lyra Silvestar")
            .price(9.90)
            .publisher("APublisher")
            .build();


        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.getIsbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.getTitle());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.getAuthor());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.getPrice());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher").isEqualTo(book.getPublisher());

    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
            {
                "isbn":"1234567890",
                "title":"Title",
                "author":"Author",
                "price":9.90,
                "publisher":"APublisher"
            }
                       
            """;

        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(9.90)
                .publisher("APublisher")
                .build()
            );
    }
}
