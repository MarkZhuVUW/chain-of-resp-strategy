package net.markz.checkout.api.chains.checkout.pricingrules.premium;

import net.markz.checkout.api.chains.checkout.pricingrules.AbstractPricingRule;
import net.markz.checkout.model.ItemDto;
import org.slf4j.Logger;

import java.util.AbstractCollection;

public class FreeDeliveryRule extends AbstractPricingRule {
    @Override
    protected Logger getLogger() {
        return null;
    }

    @Override
    protected String getClassName() {
        return null;
    }

    @Override
    protected Double apply(final ItemDto itemDto) {
        return null;
    }
}
