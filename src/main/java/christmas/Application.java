package christmas;

import christmas.domain.Order;
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

        OutputView.welcomeMessage();
        Order order = initService.getOrder();
        OutputView.benefitsMessage(order.date());
        OutputView.orderedMenu(order.menus());
        OutputView.totalAmountBeforeDiscount(order.menus());

        eventService.applyEvents(order);
    }
}
