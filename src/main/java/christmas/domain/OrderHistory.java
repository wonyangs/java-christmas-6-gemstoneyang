package christmas.domain;

import christmas.config.Menu;
import christmas.config.MenuCategory;
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
        // todo: 검증로직 추가
        if (input.equals("")) {
            return;
        }
        parseInput(input);
    }

    public OrderHistory(Map<Menu, Integer> orders) {
        this.orders.putAll(orders);
    }

    private void parseInput(String input) {
        Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .forEach(arr -> orders.put(getMenu(arr[0]), Integer.parseInt(arr[1])));
    }

    private Menu getMenu(String menuName) {
        return Menu.fromName(menuName);
    }

    public int totalPrice() {
        return orders.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int getCategoryCount(MenuCategory category) {
        return (int) orders.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .count();
    }

    @Override
    public String toString() {
        return orders.entrySet().stream()
                .map(entry -> entry.getKey().getName() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
