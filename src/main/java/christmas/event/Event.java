package christmas.event;

import christmas.domain.Order;

public interface Event {
    boolean isApplicable(Order order);

    String eventName();
}
