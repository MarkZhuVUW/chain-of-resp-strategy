package net.markz.checkout.api.chains.checkout;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.markz.checkout.api.chains.AbstractStep;
import net.markz.checkout.api.chains.Context;
import net.markz.checkout.api.chains.Response;
import net.markz.checkout.api.chains.checkout.pricingrules.PricingRuleProvider;
import net.markz.checkout.api.exception.CheckoutApiException;
import net.markz.checkout.api.services.ItemService;
import net.markz.checkout.model.GetTotalPriceResponse;
import net.markz.checkout.model.ShoppingCartDto;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CalculateTotalStep extends AbstractStep<ShoppingCartDto, GetTotalPriceResponse> {

    private final PricingRuleProvider pricingRuleProvider;
    private final ItemService itemService;

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }

    @Override
    protected void process(final Context<ShoppingCartDto> context, final Response<GetTotalPriceResponse> response) {

        final var totalPrice = context.getDto().getData().stream()
                .map(itemDto -> {
                    final var specialDto = itemService.getSpecial(itemDto.getUuid());
                    itemDto.specialDto(specialDto);

                    final var pricingRule = pricingRuleProvider.getPricingRule(specialDto.getPriceRule());
                    if(pricingRule == null) {
                        throw new CheckoutApiException(
                                HttpStatus.BAD_REQUEST,
                                String.format("pricing rule=%s is not currently supported", specialDto.getPriceRule()
                                ));
                    }

                    return pricingRule.tryApply(itemDto);
                })
                .reduce(Double::sum)
                .orElseThrow(() -> new CheckoutApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred."));

        response.setResult(new GetTotalPriceResponse().totalPrice(Double.parseDouble(String.format("%.2f", totalPrice))));
    }
}
