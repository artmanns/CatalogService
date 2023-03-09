package at.artmanns.catalogservice.domain;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
@DataJpaTest
public class BookRepositoryJpaTests {

    @Autowired
    private BookRepository bookRepository;


    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234567123";


        var book = Book.builder()
            .isbn(bookIsbn)
            .title("Title")
            .author("Author")
            .price(9.90)
            .publisher("APublisher")
            .build();

        bookRepository.save(book);

        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getIsbn()).isEqualTo(book.getIsbn());

    }
}
