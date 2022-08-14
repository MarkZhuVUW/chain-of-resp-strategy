package net.markz.checkout.api.chains;

import lombok.Data;

@Data
public final class Context<T> {
    private T dto;
}
