package christmas;

import christmas.domain.EventBenefit;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.event.ChristmasDdayEvent;
import christmas.event.DecemberGiveawayEvent;
import christmas.event.DecemberSpecialEvent;
import christmas.event.DecemberWeekdayEvent;
import christmas.event.DecemberWeekendEvent;
import christmas.service.PlannerEventService;
import christmas.service.PlannerInitService;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PlannerInitService initService = new PlannerInitService();
        PlannerEventService eventService = new PlannerEventService.Builder()
                .addEvent(new ChristmasDdayEvent())
                .addEvent(new DecemberWeekdayEvent())
                .addEvent(new DecemberWeekendEvent())
                .addEvent(new DecemberSpecialEvent())
                .addEvent(new DecemberGiveawayEvent())
                .build();

        // 안녕하세요!
        OutputView.welcomeMessage();

        // 주문 정보 입력
        Order order = initService.getOrder();

        // 혜택 미리 보기!
        OutputView.benefitsMessage(order.date());

        // 주문 메뉴
        OutputView.orderedMenu(order.menus());

        // 할인 전 총주문 금액
        int totalAmount = order.menus().totalPrice();
        OutputView.totalAmountBeforeDiscount(totalAmount);

        // 증정메뉴
        OrderHistory giveawayMenus = eventService.getGiveawayMenus(order);
        OutputView.giveawayMenu(giveawayMenus);

        // 혜택 내역
        EventBenefit benefit = eventService.applyAllEvent(order);
        OutputView.benefitHistory(benefit);

        // 총혜택 금액
        OutputView.totalBenefitAmount(benefit.totalBenefitAmount());

        // 예상 결제 금액
        int expectAmount = order.menus().totalPrice() - benefit.totalDiscountAmount();
        OutputView.totalAmountAfterDiscount(expectAmount);
    }
}
