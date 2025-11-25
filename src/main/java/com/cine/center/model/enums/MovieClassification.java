package com.cine.center.model.enums;

public enum MovieClassification {
    G("General Audience"),
    PG("Parental Guidance Suggested"),
    PG13("Parents Strongly Cautioned"),
    R("Restricted"),
    NC17("Adults Only");

    private final String description;

    MovieClassification(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
