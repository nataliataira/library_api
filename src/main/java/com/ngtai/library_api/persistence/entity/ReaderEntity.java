package com.ngtai.library_api.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderEntity {
    private Long id;
    private String name;
    private String email;
}
