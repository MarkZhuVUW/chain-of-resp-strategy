package net.markz.checkout.api.chains;

import net.markz.checkout.api.exception.CheckoutApiException;
import org.slf4j.Logger;


public abstract class AbstractStep<T, E> {

    protected abstract Logger getLogger();
    protected abstract String getClassName();


    public void tryProcess(final Context<T> context, final Response<E> response) {
        try {
            process(context, response);
            getLogger().debug("processed dto={}, response={}", context.getDto(), response);
        } catch (CheckoutApiException e) {
            // collect metrics on the operating class: getClassName() (increment exception count here )
        }
    }

    protected abstract void process(final Context<T> context, final Response<E> response);

}
