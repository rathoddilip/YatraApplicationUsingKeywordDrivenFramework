package com.selenium.keyword.utility;

public class CustomException extends Exception {
    ExceptionType type;

    public enum ExceptionType {
        FILE_NOT_EXIST,
    }

    public CustomException(ExceptionType type, String message) {

        super(message);
        this.type = type;
    }
}
