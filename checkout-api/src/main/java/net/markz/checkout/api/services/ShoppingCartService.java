package net.markz.checkout.api.services;

import lombok.extern.slf4j.Slf4j;
import net.markz.checkout.model.ItemDto;
import net.markz.checkout.model.ShoppingCartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ShoppingCartService {

    // hardcoded items. 1.1 + 2 + 0.2 + 3
    private static final ShoppingCartDto shoppingCartDto = new ShoppingCartDto().data(List.of(
            new ItemDto().price(1.0).sku("A").quantity(3),
            new ItemDto().price(2.0).sku("B").quantity(1),
            new ItemDto().price(3.0).sku("C").quantity(5)
    ));

    public ShoppingCartDto getShoppingCart(final UUID shoppingCartId) {
        return shoppingCartDto;
    }
}
