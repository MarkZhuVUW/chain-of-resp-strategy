package net.markz.checkout.api.chains;

import lombok.NonNull;
import net.markz.checkout.api.exception.CheckoutApiException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class AbstractStepChainFactory<V, T, E> {
    protected abstract Map<V, StepChain<T, E>> getCheckoutStepChainMap();

    protected StepChain<T, E> getCheckoutStepChain(@NonNull final V key) {
        final StepChain<T, E> stepChain = getCheckoutStepChainMap().get(key);

        if(stepChain == null || stepChain.getSteps().isEmpty()) {
            throw new CheckoutApiException(HttpStatus.BAD_REQUEST, String.format("Key=%s is not currently supported", key));
        }

        return stepChain;
    }

    public Response<E> process(final Context<T> context, final V key) {
        return getCheckoutStepChain(key).process(context);
    }
}
