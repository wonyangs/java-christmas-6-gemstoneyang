package christmas.event;

import christmas.config.MenuCategory;
import christmas.domain.Order;

public class DecemberWeekdayEvent implements DiscountEvent {
    private static final String EVENT_NAME = "평일 할인";
    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public boolean isApplicable(Order order) {
        int dessertCount = order.menus()
                .getCategoryCount(MenuCategory.DESSERT);
        if (dessertCount == 0) {
            return false;
        }

        return order.date()
                .isWeekDays();
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }

    @Override
    public int discountAmount(Order order) {
        int dessertCount = order.menus()
                .getCategoryCount(MenuCategory.DESSERT);

        return dessertCount * DISCOUNT_AMOUNT;
    }
}
