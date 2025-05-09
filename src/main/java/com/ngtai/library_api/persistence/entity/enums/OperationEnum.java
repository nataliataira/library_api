package com.ngtai.library_api.persistence.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum OperationEnum {
    INSERT,
    UPDATE,
    DELETE;

    public static OperationEnum getByDbOperation(final String dbOperation) {
        return Stream.of(OperationEnum.values())
                .filter(o -> o.name().startsWith(dbOperation.toUpperCase()))
                .findFirst()
                .orElseThrow();
    }
}
