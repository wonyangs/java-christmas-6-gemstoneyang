package christmas.service;

import christmas.config.Menu;
import christmas.domain.EventBenefit;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.event.DiscountEvent;
import christmas.event.Event;
import christmas.event.GiveawayEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PlannerEventService {
    private final List<Event> events; // todo: 이벤트 분리 후 관리

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

    public EventBenefit applyAllEvent(Order order) {
        EventBenefit benefit = new EventBenefit();

        events.stream()
                .filter(DiscountEvent.class::isInstance)
                .map(DiscountEvent.class::cast)
                .filter(discountEvent -> discountEvent.isApplicable(order))
                .forEach(discountEvent -> addDiscountBenefit(benefit, discountEvent, order));

        events.stream()
                .filter(GiveawayEvent.class::isInstance)
                .map(GiveawayEvent.class::cast)
                .filter(giveawayEvent -> giveawayEvent.isApplicable(order))
                .forEach(giveawayEvent -> addGiveawayBenefit(benefit, giveawayEvent, order));

        return benefit;
    }

    private void addDiscountBenefit(EventBenefit benefit, DiscountEvent discountEvent, Order order) {
        String eventName = discountEvent.eventName();
        int discountAmount = discountEvent.discountAmount(order);
        benefit.addDiscountBenefit(eventName, discountAmount);
    }

    private void addGiveawayBenefit(EventBenefit benefit, GiveawayEvent giveawayEvent, Order order) {
        String eventName = giveawayEvent.eventName();
        Map<Menu, Integer> giveawayMenus = giveawayEvent.giveawayMenus(order);
        OrderHistory giveaways = new OrderHistory(giveawayMenus);
        benefit.addGiveawayBenefit(eventName, giveaways);
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
