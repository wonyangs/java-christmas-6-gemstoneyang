package christmas.validator;

import static christmas.config.ErrorMessage.INVALID_ORDER_INPUT;
import static christmas.config.ErrorMessage.MAX_COUNT_ORDER;
import static christmas.config.ErrorMessage.ONLY_DRINK_ORDER;
import static christmas.config.MenuCategory.DRINK;

import christmas.config.Menu;
import java.util.HashSet;
import java.util.Map;

public class OrderHistoryValidator {
    private static final int MAX_ORDER_COUNT = 20;

    public static void valid(Map<Menu, Integer> orders) {
        checkDuplicateMenu(orders);
        checkOnlyDrink(orders);
        checkMaxMenuCount(orders);
    }

    private static void checkDuplicateMenu(Map<Menu, Integer> orders) {
        int uniqueSize = new HashSet<>(orders.keySet()).size();

        if (orders.size() != uniqueSize) {
            throw new IllegalArgumentException(INVALID_ORDER_INPUT.getMessage());
        }
    }

    private static void checkOnlyDrink(Map<Menu, Integer> orders) {
        boolean onlyDrink = orders.keySet()
                .stream()
                .allMatch(menu -> menu.getCategory() == DRINK);

        if (onlyDrink) {
            throw new IllegalArgumentException(ONLY_DRINK_ORDER.getMessage());
        }
    }

    private static void checkMaxMenuCount(Map<Menu, Integer> orders) {
        int totalCount = orders.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalCount > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(MAX_COUNT_ORDER.getMessage());
        }
    }
}
