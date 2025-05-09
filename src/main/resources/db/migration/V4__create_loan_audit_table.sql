CREATE TABLE IF NOT EXISTS loan_audit
(
    id              BIGINT    NOT NULL AUTO_INCREMENT,
    loan_id         BIGINT    NOT NULL,
    operation       CHAR(1)   NOT NULL,
    book_id         BIGINT,
    old_book_id     BIGINT,
    reader_id       BIGINT,
    old_reader_id   BIGINT,
    return_date     TIMESTAMP NULL,
    old_return_date TIMESTAMP NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (loan_id) REFERENCES loan (id),
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (reader_id) REFERENCES reader (id)
);
