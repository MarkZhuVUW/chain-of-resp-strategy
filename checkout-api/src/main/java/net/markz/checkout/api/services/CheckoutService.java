package net.markz.checkout.api.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.markz.checkout.api.chains.Context;
import net.markz.checkout.api.chains.checkout.TotalPriceStepChainFactory;
import net.markz.checkout.model.GetTotalPriceResponse;
import net.markz.checkout.model.PricingTier;
import net.markz.checkout.model.ShoppingCartDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CheckoutService {

    private final TotalPriceStepChainFactory totalPriceStepChainFactory;
    private final ShoppingCartService shoppingCartService;

    public GetTotalPriceResponse getTotalPrice(final UUID shoppingCartId, final PricingTier pricingTier) {

        final var shoppingCartDto = shoppingCartService.getShoppingCart(shoppingCartId);

        final var context = new Context<ShoppingCartDto>();
        context.setDto(shoppingCartDto);

        return totalPriceStepChainFactory.process(context, pricingTier).getResult();
    }

}
