package christmas;

import christmas.domain.Date;
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
        OutputView.welcomeMessage();

        PlannerInitService initService = new PlannerInitService();
        Date date = initService.getUserExpectDate();
        OrderHistory orderHistory = initService.getUserOrder();
        Order order = initService.getOrder(date, orderHistory);

        PlannerEventService eventService = new PlannerEventService.Builder()
                .addEvent(new ChristmasDdayEvent())
                .addEvent(new DecemberWeekdayEvent())
                .addEvent(new DecemberWeekendEvent())
                .addEvent(new DecemberSpecialEvent())
                .addEvent(new DecemberGiveawayEvent())
                .build();

    }
}
