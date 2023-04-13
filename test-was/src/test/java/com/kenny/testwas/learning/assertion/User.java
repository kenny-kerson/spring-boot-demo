package com.kenny.testwas.learning.assertion;

public class User {
    private final String id;
    private final String name;

    public User(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
