package at.artmanns.catalogservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format mus be valid."
        )
        private String isbn;
        @NotBlank(
                message = "The book title must be defined."
        )
        private String title;
        @NotBlank(
                message = "The author must be defined."
        )
        private String author;
        @NotNull(
                message = "The price must be defined."
        )
        @Positive (
                message = "The book price must be greater than zero."
        )
        private Double price;

        @CreationTimestamp
        private LocalDateTime createdDate;

        @UpdateTimestamp
        private LocalDateTime lastModifiedDate;

        @Version
        private int version;


}
