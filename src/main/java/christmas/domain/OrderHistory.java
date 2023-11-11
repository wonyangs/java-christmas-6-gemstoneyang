package christmas.domain;

import christmas.config.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 주문 내역을 저장하는 클래스
 */
public class OrderHistory {
    private final Map<String, Integer> orders = new HashMap<>();

    public OrderHistory(String orderInput) {
        // todo: 검증로직 추가

        if (orderInput.equals("")) {
            return;
        }

        Arrays.stream(orderInput.split(","))
                .map(s -> s.split("-"))
                .forEach(arr -> orders.put(arr[0], Integer.parseInt(arr[1])));
    }

    public int totalPrice() {
        int total = orders.entrySet().stream()
                .mapToInt(entry -> Menu.fromName(entry.getKey()).getPrice() * entry.getValue())
                .sum();

        return total;
    }

    @Override
    public String toString() {
        return orders.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
