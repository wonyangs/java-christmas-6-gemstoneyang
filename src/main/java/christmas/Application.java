package christmas;

import christmas.domain.OrderHistory;
import christmas.service.PlannerInitService;
import christmas.view.OutputView;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        PlannerInitService service = new PlannerInitService();

        OutputView.welcomeMessage();
        int date = service.getUserExpectDate();
        System.out.println(date);

        OrderHistory order = service.getUserOrder();
        System.out.println(order);
    }
}
