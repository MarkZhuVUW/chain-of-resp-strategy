package net.markz.checkout.api.chains.checkout;

import net.markz.checkout.api.chains.Context;
import net.markz.checkout.api.chains.Response;
import net.markz.checkout.api.chains.checkout.pricingrules.PricingRuleProvider;
import net.markz.checkout.api.chains.checkout.pricingrules.beginner.MultiPriceRule;
import net.markz.checkout.api.services.ItemService;
import net.markz.checkout.model.GetTotalPriceResponse;
import net.markz.checkout.model.ItemDto;
import net.markz.checkout.model.ShoppingCartDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateTotalStepTest {

    private CalculateTotalStep calculateTotalStep;

    private MultiPriceRule multiPriceRule;
    @BeforeEach
    public void beforeEach() {
        final var pricingRuleFactory = new PricingRuleProvider(multiPriceRule = mock(MultiPriceRule.class));
        pricingRuleFactory.init();

        calculateTotalStep = new CalculateTotalStep(
                pricingRuleFactory,
                new ItemService()
        );

    }

    @Test
    void getTotalPrice_callsServicesCorrectly() {
        // given

        final var itemId = UUID.randomUUID();
        final var getTotalPriceResponse = new GetTotalPriceResponse().totalPrice(3.0);
        final var shoppingCartDto = new ShoppingCartDto()
                .addDataItem(new ItemDto().price(1.0).sku("A").quantity(1).uuid(itemId))
                .addDataItem(new ItemDto().price(2.0).sku("B").quantity(3).uuid(itemId))
                .addDataItem(new ItemDto().price(3.0).sku("C").quantity(5).uuid(itemId));
        final var context = new Context<ShoppingCartDto>();
        context.setDto(shoppingCartDto);
        final var response = new Response<GetTotalPriceResponse>();
        when(multiPriceRule.tryApply(any())).thenReturn(1.0);

        // when
        calculateTotalStep.process(context, response);

        //then
        verify(multiPriceRule, times(3)).tryApply(any());
        assertEquals(getTotalPriceResponse, response.getResult());
    }
}