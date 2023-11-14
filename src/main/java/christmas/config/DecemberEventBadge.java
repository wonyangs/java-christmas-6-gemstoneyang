package christmas.config;

import java.util.Arrays;

public enum DecemberEventBadge {
    SANTA(20000, "산타"),
    TREE(10000, "트리"),
    STAR(5000, "별"),
    NONE(0, "없음");

    private final int amount;
    private final String badge;

    DecemberEventBadge(int amount, String badge) {
        this.amount = amount;
        this.badge = badge;
    }

    public static String getBadge(int totalBenefitAmount) {
        return Arrays.stream(values())
                .filter(badge -> totalBenefitAmount >= badge.amount)
                .findFirst()
                .map(badge -> badge.badge)
                .orElse(NONE.badge);
    }
}
