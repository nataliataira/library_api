package com.ngtai.library_api.dto;

public record BookDTO (
        String title,
        String author,
        Boolean loaned) {
}
