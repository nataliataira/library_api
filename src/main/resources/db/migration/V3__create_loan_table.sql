CREATE TABLE IF NOT EXISTS loan
(
    id          BIGINT    NOT NULL AUTO_INCREMENT,
    book_id     BIGINT,
    reader_id   BIGINT,
    return_date TIMESTAMP NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (reader_id) REFERENCES reader (id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);