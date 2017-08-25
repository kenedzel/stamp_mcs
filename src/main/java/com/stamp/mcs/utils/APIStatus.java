package com.stamp.mcs.utils;

/**
 * Created by kenneth on 7/19/17.
 */
public enum APIStatus {
    SUCCESS(200, "Success"),
    CREATED(201, "Created"),
    NO_CONTENT(204, "No Content"),
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(401, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    CONFLICT(409, "Conflict"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error");

    private int code;
    private String message;

    APIStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
