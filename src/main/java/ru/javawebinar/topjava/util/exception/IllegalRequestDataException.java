package ru.javawebinar.topjava.util.exception;

/**
 * @author Alexei Valchuk, 09.03.2023, email: a.valchukav@gmail.com
 */

public class IllegalRequestDataException extends RuntimeException {

    public IllegalRequestDataException(String msg) {
        super(msg);
    }
}
