package com.dio.apirest.entity.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    HOME("Home"),
    MOBILE("Mobile"),
    ENTERPRISE("Enterprise");

    private final String description;
}
