package christmas.domain;

import static christmas.config.ErrorMessage.INVALID_ORDER_INPUT;

import christmas.config.Menu;
import christmas.config.MenuCategory;
import christmas.validator.Validator;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 주문 목록을 관리하는 클래스
 */
public class OrderHistory {
    private final EnumMap<Menu, Integer> orders  = new EnumMap<>(Menu.class);

    public OrderHistory(String input) {
        Map<Menu, Integer> parsedInput = parseOrderInput(input);
        this.orders.putAll(parsedInput);
    }

    public OrderHistory(Map<Menu, Integer> parsedOrder) {
        this.orders.putAll(parsedOrder);
    }

    public static Map<Menu, Integer> parseOrderInput(String input) {
        if (!Validator.isValidOrder(input)) {
            throw new IllegalArgumentException(INVALID_ORDER_INPUT.getMessage());
        }

        Map<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);

        Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .forEach(arr -> parsedOrder.put(getMenu(arr[0]), Integer.parseInt(arr[1])));

        return parsedOrder;
    }

    private static Menu getMenu(String menuName) {
        return Menu.fromName(menuName);
    }

    public int totalPrice() {
        return orders.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int getCategoryCount(MenuCategory category) {
        return orders.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    @Override
    public String toString() {
        if (orders.isEmpty()) {
            return "없음";
        }
        return orders.entrySet().stream()
                .map(entry -> entry.getKey().getName() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
