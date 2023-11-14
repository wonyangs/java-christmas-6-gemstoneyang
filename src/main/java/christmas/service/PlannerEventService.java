package christmas.service;

import christmas.config.Menu;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.event.Event;
import christmas.event.GiveawayEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PlannerEventService {
    private final List<Event> events;

    private PlannerEventService(List<Event> events) {
        this.events = events;
    }

    public OrderHistory getGiveawayMenus(Order order) {
        List<GiveawayEvent> giveawayEvents = getGiveawayEvents();
        Map<Menu, Integer> totalGiveawayMenus = applyGiveawayEvents(order, giveawayEvents);

        return new OrderHistory(totalGiveawayMenus);
    }

    private List<GiveawayEvent> getGiveawayEvents() {
        return events.stream()
                .filter(GiveawayEvent.class::isInstance)
                .map(GiveawayEvent.class::cast)
                .toList();
    }

    private Map<Menu, Integer> applyGiveawayEvents(Order order, List<GiveawayEvent> giveawayEvents) {
        Map<Menu, Integer> totalGiveawayMenus = new EnumMap<>(Menu.class);

        giveawayEvents.stream()
                .filter(giveawayEvent -> giveawayEvent.isApplicable(order))
                .forEach(giveawayEvent -> addGiveawayMenus(totalGiveawayMenus, giveawayEvent.giveawayMenus(order)));

        return totalGiveawayMenus;
    }

    private void addGiveawayMenus(Map<Menu, Integer> totalGiveawayMenus, Map<Menu, Integer> giveawayMenus) {
        giveawayMenus.forEach((menu, count) -> totalGiveawayMenus.merge(menu, count, Integer::sum));
    }

    public static class Builder {
        private final List<Event> events = new ArrayList<>();

        public Builder addEvent(Event event) {
            events.add(event);
            return this;
        }

        public PlannerEventService build() {
            return new PlannerEventService(events);
        }
    }
}
