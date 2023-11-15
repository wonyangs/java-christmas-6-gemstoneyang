package christmas.service;

import static christmas.config.ErrorMessage.INVALID_DATE_INPUT;

import christmas.validator.Validator;
import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderHistory;

public class PlannerInitService {

    public Order getOrder(Date date, OrderHistory orderHistory) {
        return Order.of(date, orderHistory);
    }

    public Date getDate(String input) {
        if (!Validator.isValidDay(input)) {
            throw new IllegalArgumentException(INVALID_DATE_INPUT.getMessage());
        }
        int day = Integer.parseInt(input);

        return Date.of(2023, 12, day);
    }

    public OrderHistory getOrderHistory(String input) {
        // todo: 검증 로직 추가
        return new OrderHistory(input);
    }
}
