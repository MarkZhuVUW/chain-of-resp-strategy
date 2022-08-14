package net.markz.checkout.api.chains.checkout.pricingrules.beginner;


import net.markz.checkout.model.ItemDto;
import net.markz.checkout.model.PriceRule;
import net.markz.checkout.model.SpecialDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiPriceRuleTest {
    private MultiPriceRule multiPriceRule;

    @BeforeEach
    public void beforeEach() {
        multiPriceRule = new MultiPriceRule();
    }

    @Test
    void multiPriceRule1() {
        // given
        final var specialDto = new SpecialDto()
                .priceRule(PriceRule.MULTIPRICE)
                .specialPrice(0.5)
                .hitQuantity(2);
        final var itemDto = new ItemDto()
                .specialDto(specialDto)
                .price(1.0)
                .quantity(5)
                .sku("A");

        // when
        final var result = multiPriceRule.apply(itemDto);
        assertEquals(2.0, result);
    }
    @Test
    void multiPriceRule2() {
        // given
        final var specialDto = new SpecialDto()
                .priceRule(PriceRule.MULTIPRICE)
                .specialPrice(1.0)
                .hitQuantity(3);
        final var itemDto = new ItemDto()
                .specialDto(specialDto)
                .price(2.0)
                .quantity(3)
                .sku("A");

        // when
        final var result = multiPriceRule.apply(itemDto);
        assertEquals(1.0, result);
    }

    @Test
    void multiPriceRule3() {
        // given
        final var specialDto = new SpecialDto()
                .priceRule(PriceRule.MULTIPRICE)
                .specialPrice(0.5)
                .hitQuantity(5);
        final var itemDto = new ItemDto()
                .specialDto(specialDto)
                .price(1.0)
                .quantity(4)
                .sku("A");

        // when
        final var result = multiPriceRule.apply(itemDto);
        assertEquals(4.0, result);
    }

}