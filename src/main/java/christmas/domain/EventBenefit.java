package christmas.domain;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        if (benefits.isEmpty()) {
            return "없음";
        }
        return benefits.entrySet().stream()
                .map(entry -> printBenefit(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }

    private String printBenefit(String eventName, int amount) {
        String formattedNumber = NumberFormat.getNumberInstance().format(amount);
        return String.format("%s: -%s원", eventName, formattedNumber);
    }
}
