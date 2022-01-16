package com.vla.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RoleUser {
    ERROR(0),
    Teacher(1),
    Student(2);

    @Getter
    private final int value;

}
