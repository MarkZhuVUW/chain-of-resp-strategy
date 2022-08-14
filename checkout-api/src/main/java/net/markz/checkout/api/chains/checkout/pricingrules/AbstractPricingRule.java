package net.markz.checkout.api.chains.checkout.pricingrules;

import net.markz.checkout.api.exception.CheckoutApiException;
import net.markz.checkout.model.ItemDto;
import org.slf4j.Logger;

public abstract class AbstractPricingRule {

    protected abstract Logger getLogger();
    protected abstract String getClassName();

    protected abstract Double apply(final ItemDto itemDto);

    public Double tryApply(final ItemDto itemDto) {
        try {
            final var result = apply(itemDto);
            getLogger().debug("applied pricing rule={} to item={}", itemDto.getSpecialDto().getPriceRule(), itemDto);
            return result;
        } catch (CheckoutApiException e) {
            // collect metrics on the operating class: getClassName() (increment exception count here)
            return null;
        }
    }
}
