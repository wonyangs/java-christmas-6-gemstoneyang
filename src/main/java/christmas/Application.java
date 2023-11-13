package christmas;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.service.PlannerInitService;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.welcomeMessage();

        PlannerInitService service = new PlannerInitService();
        Date date = service.getUserExpectDate();
        OrderHistory orderHistory = service.getUserOrder();
        Order order = service.getOrder(date, orderHistory);


    }
}
