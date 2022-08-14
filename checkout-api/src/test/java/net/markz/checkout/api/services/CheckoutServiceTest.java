package net.markz.checkout.api.services;

import net.markz.checkout.api.chains.Response;
import net.markz.checkout.api.chains.checkout.TotalPriceStepChainFactory;
import net.markz.checkout.model.GetTotalPriceResponse;
import net.markz.checkout.model.ItemDto;
import net.markz.checkout.model.PricingTier;
import net.markz.checkout.model.ShoppingCartDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    private TotalPriceStepChainFactory totalPriceStepChainFactory;
    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private CheckoutService checkoutService;

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    void getTotalPrice_callsServicesCorrectly() {
        // given
        final var shoppingCartId = UUID.randomUUID();
        final var pricingTier = PricingTier.BEGINNER;
        final var itemId = UUID.randomUUID();
        final var getTotalPriceResponse = new GetTotalPriceResponse().totalPrice(1.0);
        final var shoppingCartDto = new ShoppingCartDto()
                .addDataItem(new ItemDto().price(1.0).sku("A").quantity(1).uuid(itemId));
        final var response = new Response<GetTotalPriceResponse>();
        response.setResult(getTotalPriceResponse);
        when(shoppingCartService.getShoppingCart(shoppingCartId)).thenReturn(shoppingCartDto);
        when(totalPriceStepChainFactory.process(any(), any()))
                .thenReturn(response);

        // when
        final var result = checkoutService.getTotalPrice(shoppingCartId, pricingTier);

        //then
        assertEquals(getTotalPriceResponse, result);
    }

}