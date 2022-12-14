package net.markz.checkout.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CheckoutApiException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CheckoutApiException(final HttpStatus httpStatus, final String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
