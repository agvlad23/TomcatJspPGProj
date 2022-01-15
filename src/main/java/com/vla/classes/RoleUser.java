package com.vla.classes;

public enum RoleUser {
    ERROR(0),
    Teacher(1),
    Student(2);

    private final int value;

    RoleUser(int role) {
        value=role;
    }

    public int getValue() {
        return value;
    }
}
