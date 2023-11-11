package christmas.domain;

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
        Arrays.stream(orderInput.split(","))
                .map(s -> s.split("-"))
                .forEach(arr -> orders.put(arr[0], Integer.parseInt(arr[1])));
    }

    @Override
    public String toString() {
        return orders.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
