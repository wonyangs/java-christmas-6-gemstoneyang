package christmas.view;

import christmas.domain.Date;
import christmas.domain.OrderHistory;

public class OutputView {
    public static void welcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void benefitsMessage(Date date) {
        System.out.println(date + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void showOrderedMenu(OrderHistory orderedMenu) {
        System.out.println("\n<주문 메뉴>");
        System.out.println(orderedMenu);
    }
}
