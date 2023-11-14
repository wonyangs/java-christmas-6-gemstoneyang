package christmas.event;

import christmas.config.Menu;
import christmas.domain.Date;
import christmas.domain.Order;
import java.util.EnumMap;
import java.util.Map;

public class DecemberGiveawayEvent implements GiveawayEvent {
    private static final String EVENT_NAME = "증정 이벤트";
    private static final Date EVENT_START_DATE = Date.of(2023,12,1);
    private static final Date EVENT_END_DATE = Date.of(2023,12,31);
    private static final int MINIMUM_PRICE = 120_000;

    @Override
    public boolean isApplicable(Order order) {
        if (!inEventDuration(order)) {
            return false;
        }
        int totalPrice = order.menus().totalPrice();

        return totalPrice >= MINIMUM_PRICE;
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
    public Map<Menu, Integer> giveawayMenus(Order order) {
        EnumMap<Menu, Integer> giveaways  = new EnumMap<>(Menu.class);

        giveaways.put(Menu.CHAMPAGNE, 1);

        return giveaways;
    }
}
