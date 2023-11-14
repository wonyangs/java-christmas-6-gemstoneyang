package christmas.service;

import christmas.event.Event;
import java.util.ArrayList;
import java.util.List;

public class PlannerEventService {
    private final List<Event> events;

    private PlannerEventService(List<Event> events) {
        this.events = events;
    }

    public static class Builder {
        private final List<Event> events = new ArrayList<>();

        public Builder addEvent(Event event) {
            events.add(event);
            return this;
        }

        public PlannerEventService build() {
            return new PlannerEventService(events);
        }
    }
}
