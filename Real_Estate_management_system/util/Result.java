package com.realestate.util;

/**
 * Simple generic result wrapper.
 */
public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }

    public static <T> Result<T> ok(T data) {
        return new Result<>(true, "OK", data);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(false, msg, null);
    }
}
