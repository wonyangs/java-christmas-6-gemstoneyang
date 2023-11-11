package christmas.service;

import christmas.view.InputView;

public class PlannerInitService {

    public int getUserExpectDate() {
        while (true) {
            try {
                String date = InputView.getExpectDate();

                // todo: 검증 로직 추가

                return Integer.parseInt(date);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
