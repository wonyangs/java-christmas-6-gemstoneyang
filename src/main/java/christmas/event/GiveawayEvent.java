package christmas.event;

import christmas.domain.Order;
import christmas.domain.OrderHistory;

public interface GiveawayEvent extends Event {

    OrderHistory giveawayMenus(Order order);
}
