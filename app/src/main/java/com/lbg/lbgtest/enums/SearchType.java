package com.lbg.lbgtest.enums;

/**
 * Enum for Search Type
 */
public enum SearchType {
    METHOD("method"),
    TRACK("track"),
    ALBUM("album"),
    ARTIST("artist"),
    API_KEY("api_key"),
    FORMAT("format");


    private final String key;


    SearchType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
