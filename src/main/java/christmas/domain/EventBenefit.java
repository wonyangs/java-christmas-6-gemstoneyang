package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class EventBenefit {
    private final Map<String, Integer> benefits = new HashMap<>();
    private int totalDiscount = 0;

    public void addDiscountBenefit(String eventName, int amount) {
        benefits.put(eventName, amount);
        totalDiscount += amount;
    }

    public void addGiveawayBenefit(String eventName, OrderHistory giveaways) {
        int giveawayAmount = giveaways.totalPrice();
        benefits.put(eventName, giveawayAmount);
    }

    public int totalBenefitAmount() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int totalDiscountAmount() {
        return totalDiscount;
    }
}
