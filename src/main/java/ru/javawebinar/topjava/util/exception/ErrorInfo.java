package ru.javawebinar.topjava.util.exception;

/**
 * @author Alexei Valchuk, 09.03.2023, email: a.valchukav@gmail.com
 */

public class ErrorInfo {

    private final String url;
    private final ErrorType type;
    private final String typeMessage;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String typeMessage, String... details) {
        this.url = url.toString();
        this.type = type;
        this.typeMessage = typeMessage;
        this.details = details;
    }
}
