package christmas.event;

import christmas.config.Menu;
import christmas.domain.Order;
import java.util.Map;

public interface GiveawayEvent extends Event {

    Map<Menu, Integer> giveawayMenus(Order order);
}
