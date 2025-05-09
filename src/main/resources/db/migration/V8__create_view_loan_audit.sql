CREATE VIEW view_loan_audit AS
    SELECT loan_id,
           book_id,
           old_book_id,
           reader_id,
           old_reader_id,
           return_date,
           old_return_date,
           created_at,
           operation
    FROM loan_audit
    ORDER BY created_at;