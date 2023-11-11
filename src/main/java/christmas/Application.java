package christmas;

import christmas.service.PlannerInitService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PlannerInitService service = new PlannerInitService();

        OutputView.welcomeMessage();
        int date = service.getUserExpectDate();
        System.out.println(date);
    }
}
