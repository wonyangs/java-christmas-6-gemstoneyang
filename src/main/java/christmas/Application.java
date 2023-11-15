package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.PlannerController;
import christmas.event.ChristmasDdayEvent;
import christmas.event.DecemberGiveawayEvent;
import christmas.event.DecemberSpecialEvent;
import christmas.event.DecemberWeekdayEvent;
import christmas.event.DecemberWeekendEvent;
import christmas.service.PlannerEventService;
import christmas.service.PlannerInitService;

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

        PlannerController controller = new PlannerController(initService, eventService);

        controller.run();
        Console.close();
    }
}
