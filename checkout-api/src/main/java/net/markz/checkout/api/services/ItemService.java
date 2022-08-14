package net.markz.checkout.api.services;

import lombok.extern.slf4j.Slf4j;
import net.markz.checkout.model.PriceRule;
import net.markz.checkout.model.SpecialDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ItemService {


    private static final SpecialDto specialDto = new SpecialDto()
            .specialPrice(0.1).hitQuantity(2).priceRule(PriceRule.MULTIPRICE);

    public SpecialDto getSpecial(final UUID itemId) {
        return specialDto;
    }
}


