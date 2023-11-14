package christmas.event;

import christmas.domain.Date;
import christmas.domain.Order;

public class ChristmasDdayEvent implements DiscountEvent {
    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private static final Date EVENT_START_DATE = Date.of(2023,12,1);
    private static final Date EVENT_END_DATE = Date.of(2023,12,25);
    private static final int MAX_DISCOUNT = 3_400;
    private static final int DISCOUNT_PER_DAY = 100;


    @Override
    public boolean isApplicable(Order order) {
        return inEventDuration(order);
    }

    private boolean inEventDuration(Order order) {
        return order.date()
                .isInRange(EVENT_START_DATE, EVENT_END_DATE);
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }

    @Override
    public int discountAmount(Order order) {
        Date orderDate = order.date();
        int diffDay = Date.of(2023, 12, 25)
                .daysBetween(orderDate);

        return MAX_DISCOUNT - (diffDay * DISCOUNT_PER_DAY);
    }
}
