package com.ngtai.library_api.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {
    private Long id;
    private Long readerId;
    private Long bookId;
    private OffsetDateTime createdAt;
    private OffsetDateTime returnDate;
}
