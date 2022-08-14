package net.markz.checkout.api.chains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Response<T> {
    private T result;
}
