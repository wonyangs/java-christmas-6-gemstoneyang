package christmas.domain;

import christmas.config.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Orders {
    private final Map<String, Integer> orders  = new HashMap<>();

    public Orders(String input) {
        // todo: 검증로직 추가
        if (input.equals("")) {
            return;
        }
        parseInput(input);
    }

    private void parseInput(String input) {
        Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .forEach(arr -> orders.put(arr[0], Integer.parseInt(arr[1])));
    }

    public int totalPrice() {
        return orders.entrySet().stream()
                .mapToInt(entry -> Menu.fromName(entry.getKey()).getPrice() * entry.getValue())
                .sum();
    }

    @Override
    public String toString() {
        return orders.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
