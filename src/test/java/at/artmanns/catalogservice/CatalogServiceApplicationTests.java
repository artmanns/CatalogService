package at.artmanns.catalogservice;

import at.artmanns.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {


    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBookCreated() {

        var expectBook = Book.builder()
                .isbn("1234567891")
                .title( "Northern Lights")
                .author("Lyra Silvestar")
                .price(9.90)
                .build();


        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expectBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                   assertThat(actualBook).isNotNull();
                   assertThat(actualBook.getIsbn())
                           .isEqualTo(expectBook.getIsbn());
                });
    }

}
