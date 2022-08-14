package net.markz.checkout.api.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.markz.checkout.api.CheckoutApiDelegate;
import net.markz.checkout.api.services.CheckoutService;

import net.markz.checkout.model.GetTotalPriceResponse;
import net.markz.checkout.model.PricingTier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;


@AllArgsConstructor
@Builder
@Component
@Slf4j
public class CheckoutController implements CheckoutApiDelegate {

    private final CheckoutService checkoutService;

    @Override
    public ResponseEntity<GetTotalPriceResponse> getTotalPrice(final UUID shoppingCartId, final PricingTier pricingTier) {

        return ResponseEntity.ok(
                checkoutService.getTotalPrice(shoppingCartId, pricingTier)
        );

    }
}
