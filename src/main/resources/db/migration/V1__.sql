CREATE TABLE books
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    isbn               VARCHAR(255),
    title              VARCHAR(255),
    author             VARCHAR(255),
    price              DOUBLE PRECISION                        NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    version            INTEGER                                 NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (id)
);