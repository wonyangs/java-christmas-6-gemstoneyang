package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.welcomeMessage();
        String date = InputView.getExpectDate();
        System.out.println(date);
    }
}
