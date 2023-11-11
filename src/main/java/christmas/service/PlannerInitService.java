package christmas.service;

import christmas.domain.Date;
import christmas.domain.Orders;
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

    public Orders getUserOrder() {
        while (true) {
            try {
                String input = InputView.getOrder();

                return new Orders(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
