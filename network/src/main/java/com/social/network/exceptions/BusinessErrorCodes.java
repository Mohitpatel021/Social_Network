package com.social.network.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No Code Found"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Incorrect Current Password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "New Password Does Not Match"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "User Account is Locked"),
    ACCOUNT_DISABLE(303, HttpStatus.FORBIDDEN, "User Account is disable"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "user/password is incorrect"),
    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatusCode;

    BusinessErrorCodes(int code, HttpStatus httpStatusCode, String description) {
        this.code = code;
        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }
}
