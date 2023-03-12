package ru.javawebinar.topjava.util.exception;

/**
 * @author Alexei Valchuk, 09.03.2023, email: a.valchukav@gmail.com
 */

public enum ErrorType {
    APP_ERROR("error.appError"),
    DATA_NOT_FOUND("error.dataNotFound"),
    DATA_ERROR("error.dataError"),
    VALIDATION_ERROR("error.validationError"),
    WRONG_REQUEST("error.wrongRequest");

    private final String errorCode;

    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
