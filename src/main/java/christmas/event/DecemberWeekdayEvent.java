package christmas.event;

import christmas.config.MenuCategory;
import christmas.domain.Date;
import christmas.domain.Order;

public class DecemberWeekdayEvent implements DiscountEvent {
    private static final String EVENT_NAME = "평일 할인";
    private static final Date EVENT_START_DATE = Date.of(2023,12,1);
    private static final Date EVENT_END_DATE = Date.of(2023,12,31);
    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public boolean isApplicable(Order order) {
        if (!inEventDuration(order)) {
            return false;
        }
        if (getDessertCount(order) == 0) {
            return false;
        }
        return order.date()
                .isWeekDays();
    }

    private boolean inEventDuration(Order order) {
        return order.date()
                .isInRange(EVENT_START_DATE, EVENT_END_DATE);
    }

    private int getDessertCount(Order order) {
        return order.menus()
                .getCategoryCount(MenuCategory.DESSERT);
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }

    @Override
    public int discountAmount(Order order) {
        int dessertCount = getDessertCount(order);

        return dessertCount * DISCOUNT_AMOUNT;
    }
}
