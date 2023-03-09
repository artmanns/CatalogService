package at.artmanns.catalogservice;

import at.artmanns.catalogservice.domain.Book;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {


    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void whenPostRequestThenBookCreated() {

        var expectBook = Book.builder()
            .isbn("1234567891")
            .title("Northern Lights")
            .author("Lyra Silvestar")
            .price(9.90)
            .publisher("APublisher")
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
