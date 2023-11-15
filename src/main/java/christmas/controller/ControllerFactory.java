package christmas.controller;

import christmas.event.ChristmasDdayEvent;
import christmas.event.DecemberGiveawayEvent;
import christmas.event.DecemberSpecialEvent;
import christmas.event.DecemberWeekdayEvent;
import christmas.event.DecemberWeekendEvent;
import christmas.service.PlannerEventService;
import christmas.service.PlannerInitService;

public class ControllerFactory {

    public static PlannerController createPlannerController() {
        PlannerInitService initService = new PlannerInitService();
        PlannerEventService eventService = new PlannerEventService.Builder()
                .addEvent(new ChristmasDdayEvent())
                .addEvent(new DecemberWeekdayEvent())
                .addEvent(new DecemberWeekendEvent())
                .addEvent(new DecemberSpecialEvent())
                .addEvent(new DecemberGiveawayEvent())
                .build();

        return new PlannerController(initService, eventService);
    }
}
