package christmas.view;

import christmas.domain.Date;
import christmas.domain.EventBenefit;
import christmas.domain.OrderHistory;
import java.text.NumberFormat;

public class OutputView {
    public static void welcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void benefitsMessage(Date date) {
        System.out.println(date + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void orderedMenu(OrderHistory orderedMenu) {
        System.out.println("\n<주문 메뉴>");
        System.out.println(orderedMenu);
    }

    public static void totalAmountBeforeDiscount(int totalAmount) {
        String formattedNumber = formatAmount(totalAmount);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(formattedNumber + "원");
    }

    public static void giveawayMenu(OrderHistory giveawayMenus) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(giveawayMenus);
    }

    public static void benefitHistory(EventBenefit benefit) {
        System.out.println("\n<혜택 내역>");
        System.out.println(benefit);
    }

    public static void totalBenefitAmount(int benefitAmount) {
        String formattedNumber = formatAmount(benefitAmount);

        System.out.println("\n<총혜택 금액>");
        if (benefitAmount == 0) {
            System.out.println("0원");
            return;
        }
        System.out.printf("-%s원\n", formattedNumber);
    }

    public static void totalAmountAfterDiscount(int totalAmount) {
        String formattedNumber = formatAmount(totalAmount);

        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(formattedNumber + "원");
    }

    public static void decemberBadge(String badgeName) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badgeName);
    }

    private static String formatAmount(int amount) {
        return NumberFormat.getNumberInstance()
                .format(amount);
    }
}
