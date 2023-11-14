package christmas.event;

import christmas.domain.Date;
import christmas.domain.Order;
import java.util.HashSet;
import java.util.Set;

public class DecemberSpecialEvent implements DiscountEvent {
    private static final String EVENT_NAME = "특별 할인";
    private static final Date EVENT_START_DATE = Date.of(2023,12,1);
    private static final Date EVENT_END_DATE = Date.of(2023,12,31);
    private static final Set<Integer> DECEMBER_EVENT_DAYS = Set.of(3, 10, 17, 24, 25, 31);
    private static final int DISCOUNT_AMOUNT = 1_000;

    private static final Set<Date> eventDays = createEventDaySet();

    @Override
    public boolean isApplicable(Order order) {
        if (!inEventDuration(order)) {
            return false;
        }
        return isEventDay(order);
    }

    private boolean inEventDuration(Order order) {
        return order.date()
                .isInRange(EVENT_START_DATE, EVENT_END_DATE);
    }

    private boolean isEventDay(Order order) {
        Date orderDate = order.date();

        return eventDays.contains(orderDate);
    }

    private static Set<Date> createEventDaySet() {
        Set<Date> eventDays = new HashSet<>();

        for (int day : DECEMBER_EVENT_DAYS) {
            eventDays.add(Date.of(2023, 12, day));
        }

        return eventDays;
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }

    @Override
    public int discountAmount(Order order) {
        return DISCOUNT_AMOUNT;
    }
}
