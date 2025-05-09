package com.ngtai.library_api.persistence.entity;

import com.ngtai.library_api.persistence.entity.enums.OperationEnum;

import java.time.OffsetDateTime;

public record LoanAuditEntity (
        Long loanId,
        Long readerId,
        Long oldReaderId,
        Long bookId,
        Long oldBookId,
        OffsetDateTime returnDate,
        OffsetDateTime oldReturnDate,
        OffsetDateTime createdAt,
        OperationEnum operation) {
}
