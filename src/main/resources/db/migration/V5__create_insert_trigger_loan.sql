CREATE TRIGGER trg_insert_audit_loan
    AFTER INSERT ON loan
    FOR EACH ROW
    INSERT INTO loan_audit (
        loan_id,
        operation,
        book_id,
        reader_id,
        return_date
    ) VALUES (
        NEW.id,
        'I',
        NEW.book_id,
        NEW.reader_id,
        NEW.return_date
);