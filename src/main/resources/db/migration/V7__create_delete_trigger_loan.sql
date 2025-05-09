CREATE TRIGGER  trg_delete_audit_loan
    BEFORE DELETE ON loan
    FOR EACH ROW
    INSERT INTO loan_audit (
        loan_id,
        operation,
        old_book_id,
        old_reader_id,
        old_return_date
    ) VALUES (
        OLD.id,
        'D',
        OLD.book_id,
        OLD.reader_id,
        OLD.return_date
);