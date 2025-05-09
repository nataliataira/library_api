CREATE TRIGGER  trg_update_audit_loan
    BEFORE UPDATE ON loan
    FOR EACH ROW
    INSERT INTO loan_audit (
        loan_id,
        operation,
        book_id,
        old_book_id,
        reader_id,
        old_reader_id,
        return_date,
        old_return_date
    ) VALUES (
        OLD.id,
        'U',
        NEW.book_id,
        OLD.book_id,
        NEW.reader_id,
        OLD.reader_id,
        NEW.return_date,
        OLD.return_date
);