package christmas.controller;

import christmas.domain.Date;
import christmas.domain.EventBenefit;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.service.PlannerEventService;
import christmas.service.PlannerInitService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private final PlannerInitService initService;
    private final PlannerEventService eventService;

    public PlannerController(PlannerInitService initService, PlannerEventService eventService) {
        this.initService = initService;
        this.eventService = eventService;
    }

    public void run() {
        OutputView.welcomeMessage();

        Order order = getUserOrder();
        displayOrderInfo(order);

        EventBenefit benefit = applyEvents(order);
        displayBenefitInfo(order, benefit);
    }

    private Order getUserOrder() {
        Date userDate = getUserInputDate();
        OrderHistory orderHistory = getUserInputOrderHistory();

        return initService.getOrder(userDate, orderHistory);
    }

    private Date getUserInputDate() {
        while (true) {
            try {
                String dateInput = InputView.getExpectDate();
                return initService.getDate(dateInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderHistory getUserInputOrderHistory() {
        while (true) {
            try {
                String orderInput = InputView.getOrder();
                return initService.getOrderHistory(orderInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayOrderInfo(Order order) {
        OutputView.benefitsMessage(order.date());

        // 주문 메뉴
        OutputView.orderedMenu(order.menus());

        // 할인 전 총주문 금액
        int totalAmount = order.menus()
                .totalPrice();
        OutputView.totalAmountBeforeDiscount(totalAmount);
    }

    private EventBenefit applyEvents(Order order) {
        // 증정 메뉴
        OrderHistory giveawayMenus = eventService.getGiveawayMenus(order);
        OutputView.giveawayMenu(giveawayMenus);

        return eventService.applyAllEvent(order);
    }

    private void displayBenefitInfo(Order order, EventBenefit benefit) {
        // 혜택 내역
        OutputView.benefitHistory(benefit);

        // 총혜택 금액
        OutputView.totalBenefitAmount(benefit.totalBenefitAmount());

        // 할인 후 예상 결제 금액
        int expectAmount = order.menus().totalPrice() - benefit.totalDiscountAmount();
        OutputView.totalAmountAfterDiscount(expectAmount);

        // 12월 이벤트 배지
        String badge = eventService.getDecemberBadge(benefit);
        OutputView.decemberBadge(badge);
    }
}
