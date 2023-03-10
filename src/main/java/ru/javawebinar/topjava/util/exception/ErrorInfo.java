package ru.javawebinar.topjava.util.exception;

/**
 * @author Alexei Valchuk, 09.03.2023, email: a.valchukav@gmail.com
 */

public class ErrorInfo {

    private final String url;
    private final ErrorType type;
    private final String detail;

    public ErrorInfo(CharSequence url, ErrorType type, String detail) {
        this.url = url.toString();
        this.type = type;
        this.detail = detail;
    }
}
