package christmas.event;

import christmas.config.Menu;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import java.util.EnumMap;

public class DecemberGiveawayEvent implements GiveawayEvent {
    private static final int MINIMUM_PRICE = 120_000;
    private static final String EVENT_NAME = "증정 이벤트";

    @Override
    public boolean isApplicable(Order order) {
        int totalPrice = order.totalPrice();

        return totalPrice >= MINIMUM_PRICE;
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }

    @Override
    public OrderHistory giveawayMenus(Order order) {
        EnumMap<Menu, Integer> giveaways  = new EnumMap<>(Menu.class);

        giveaways.put(Menu.CHAMPAGNE, 1);

        return new OrderHistory(giveaways);
    }
}
