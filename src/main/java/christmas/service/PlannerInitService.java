package christmas.service;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.view.InputView;

public class PlannerInitService {

    public Order getOrder() {
        Date date = getUserExpectDate();
        OrderHistory orderHistory = getUserOrder();
        return Order.of(date, orderHistory);
    }

    private Date getUserExpectDate() {
        while (true) {
            try {
                String input = InputView.getExpectDate();

                // todo: 검증 로직 추가
                int day = Integer.parseInt(input);

                return Date.of(2023, 12, day);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderHistory getUserOrder() {
        while (true) {
            try {
                String input = InputView.getOrder();

                return new OrderHistory(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
