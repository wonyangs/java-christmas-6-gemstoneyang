package christmas.view;

import christmas.domain.Date;

public class OutputView {
    public static void welcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void benefitsMessage(Date date) {
        System.out.println(date + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
