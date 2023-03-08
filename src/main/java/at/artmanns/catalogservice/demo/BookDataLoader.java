package at.artmanns.catalogservice.demo;

import at.artmanns.catalogservice.domain.BookRepository;
import at.artmanns.catalogservice.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class BookDataLoader {

    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.builder()
                .isbn("1234567891")
                .title( "Northern Lights")
                .author("Lyra Silvestar")
                .price(9.90)
                .build();

        var book2 = Book.builder()
                .isbn("1234567892")
                .title( "Polar Journey")
                .author("Iorek PolarsonIorek Polarson")
                .price(12.90)
                .build();


        bookRepository.saveAll(List.of(book1, book2));
    }

}
