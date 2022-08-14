package net.markz.checkout.api.chains.checkout;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.markz.checkout.api.chains.AbstractStepChainFactory;
import net.markz.checkout.api.chains.StepChain;
import net.markz.checkout.model.GetTotalPriceResponse;
import net.markz.checkout.model.PricingTier;
import net.markz.checkout.model.ShoppingCartDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class TotalPriceStepChainFactory extends AbstractStepChainFactory<PricingTier, ShoppingCartDto, GetTotalPriceResponse> {

    private Map<PricingTier, StepChain<ShoppingCartDto, GetTotalPriceResponse>> totalPriceStepChainMap;
    private final CalculateTotalStep calculateTotalStep;

    @PostConstruct
    public void init() {
        totalPriceStepChainMap = new HashMap<>();

        totalPriceStepChainMap.put(PricingTier.BEGINNER, buildBeginnerTotalPriceChain());
        totalPriceStepChainMap.put(PricingTier.PREMIUM, buildPremiumTotalPriceChain());
    }

    protected StepChain<ShoppingCartDto, GetTotalPriceResponse> buildBeginnerTotalPriceChain() {

        return StepChain.<ShoppingCartDto, GetTotalPriceResponse>builder()
                .step(calculateTotalStep)
                .build();
    }

    protected StepChain<ShoppingCartDto, GetTotalPriceResponse> buildPremiumTotalPriceChain() {

        return StepChain.<ShoppingCartDto, GetTotalPriceResponse>builder()
                .build();
    }

    @Override
    protected Map<PricingTier, StepChain<ShoppingCartDto, GetTotalPriceResponse>> getCheckoutStepChainMap() {
        return totalPriceStepChainMap;
    }
}
