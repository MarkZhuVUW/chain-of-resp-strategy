package net.markz.checkout.api.chains.checkout.pricingrules.beginner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.markz.checkout.api.chains.checkout.pricingrules.AbstractPricingRule;
import net.markz.checkout.model.ItemDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class MultiPriceRule extends AbstractPricingRule {

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }


    @Override
    protected Double apply(final ItemDto itemDto) {
        final var specialDto = itemDto.getSpecialDto();

        final var quantity = itemDto.getQuantity();
        final var price = itemDto.getPrice();
        final var hitQuantity = specialDto.getHitQuantity();
        final var specialPrice = specialDto.getSpecialPrice();


        return specialPrice * (quantity / hitQuantity) + price * (quantity % hitQuantity);
    }
}
