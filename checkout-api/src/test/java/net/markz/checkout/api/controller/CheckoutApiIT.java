package net.markz.checkout.api.controller;

import net.markz.checkout.api.exception.CheckoutApiException;
import net.markz.checkout.model.PricingTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CheckoutApiIT {

    @Autowired
    private CheckoutController checkoutController;

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    void beginnerPriceTier_correctTotalPrice() {
        // given
        final var shoppingCartId = UUID.randomUUID();
        final var pricingTier = PricingTier.BEGINNER;

        // when
        final var result = checkoutController.getTotalPrice(shoppingCartId, pricingTier);

        //then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(6.3, Objects.requireNonNull(result.getBody()).getTotalPrice());

    }

    @Test
    void premiumPriceTier_correctTotalPrice() {
        // given
        final var shoppingCartId = UUID.randomUUID();
        final var pricingTier = PricingTier.PREMIUM;

        // when
        final var result = assertThrows(
                CheckoutApiException.class,
                () -> checkoutController.getTotalPrice(shoppingCartId, pricingTier)
        );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, result.getHttpStatus());
        assertEquals("Key=Premium is not currently supported", result.getMessage());

    }


}