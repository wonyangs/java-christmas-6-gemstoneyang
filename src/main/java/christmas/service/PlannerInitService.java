package christmas.service;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.view.InputView;

public class PlannerInitService {

    public Date getUserExpectDate() {
        while (true) {
            try {
                String input = InputView.getExpectDate();

                // todo: 검증 로직 추가
                int day = Integer.parseInt(input);

                return new Date(2023, 12, day);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public OrderHistory getUserOrder() {
        while (true) {
            try {
                String input = InputView.getOrder();

                return new OrderHistory(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Order getOrder(Date date, OrderHistory orderHistory) {
        return Order.of(date, orderHistory);
    }
}
