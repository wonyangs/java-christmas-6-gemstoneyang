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
    private final List<DiscountEvent> discountEvents;
    private final List<GiveawayEvent> giveawayEvents;

    public OrderHistory getGiveawayMenus(Order order) {
        Map<Menu, Integer> totalGiveawayMenus = new EnumMap<>(Menu.class);

        giveawayEvents.stream()
                .filter(giveawayEvent -> giveawayEvent.isApplicable(order))
                .forEach(giveawayEvent -> addGiveawayMenus(totalGiveawayMenus, giveawayEvent.giveawayMenus(order)));

        return new OrderHistory(totalGiveawayMenus);
    }

    private void addGiveawayMenus(Map<Menu, Integer> totalGiveawayMenus, Map<Menu, Integer> giveawayMenus) {
        giveawayMenus.forEach((menu, count) -> totalGiveawayMenus.merge(menu, count, Integer::sum));
    }

    public EventBenefit applyAllEvent(Order order) {
        EventBenefit benefit = new EventBenefit();

        discountEvents.stream()
                .filter(discountEvent -> discountEvent.isApplicable(order))
                .forEach(discountEvent -> addDiscountBenefit(benefit, discountEvent, order));

        giveawayEvents.stream()
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

    private PlannerEventService(List<DiscountEvent> discountEvents, List<GiveawayEvent> giveawayEvents) {
        this.discountEvents = discountEvents;
        this.giveawayEvents = giveawayEvents;
    }

    public static class Builder {
        private final List<DiscountEvent> discountEvents = new ArrayList<>();
        private final List<GiveawayEvent> giveawayEvents = new ArrayList<>();

        public Builder addEvent(Event event) {
            if (event instanceof DiscountEvent discountEvent) {
                discountEvents.add(discountEvent);
            }
            if (event instanceof GiveawayEvent giveawayEvent) {
                giveawayEvents.add(giveawayEvent);
            }
            return this;
        }

        public PlannerEventService build() {
            return new PlannerEventService(discountEvents, giveawayEvents);
        }
    }
}
