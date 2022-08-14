package net.markz.checkout.api.chains.checkout.pricingrules;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.markz.checkout.api.chains.checkout.pricingrules.beginner.MultiPriceRule;
import net.markz.checkout.api.chains.checkout.pricingrules.premium.FreeDeliveryRule;
import net.markz.checkout.model.PriceRule;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class PricingRuleProvider {

    @NonNull
    private final MultiPriceRule multiPriceRule;
    @NonNull
    private final FreeDeliveryRule freeDeliveryRule;

    private Map<PriceRule, AbstractPricingRule> pricingRulesMap;
    @PostConstruct
    public void init() {
        pricingRulesMap = new HashMap<>();

        pricingRulesMap.put(PriceRule.MULTIPRICE, multiPriceRule);
        pricingRulesMap.put(PriceRule.FREEDELIVERY, freeDeliveryRule);

    }
    public AbstractPricingRule getPricingRule(final PriceRule priceRule) {
        return pricingRulesMap.get(priceRule);
    }
}
