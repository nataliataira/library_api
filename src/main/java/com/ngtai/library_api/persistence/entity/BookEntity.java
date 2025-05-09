package com.ngtai.library_api.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    private Long id;
    private String title;
    private String author;
    private Boolean loaned;
}
