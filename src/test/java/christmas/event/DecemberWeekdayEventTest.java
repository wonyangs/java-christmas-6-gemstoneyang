package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("DecemberWeekdayEvent 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DecemberWeekdayEventTest {
    private static final DecemberWeekdayEvent event = new DecemberWeekdayEvent();
    private static Order nonApplicableOrder1;
    private static Order nonApplicableOrder2;
    private static Order applicableOrder;

    @BeforeAll
    static void setUp() {
        Date date1 = Date.of(2023, 12, 3); // 평일
        Date date2 = Date.of(2023, 12, 23); // 주말
        OrderHistory order1 = new OrderHistory("티본스테이크-1");
        OrderHistory order2 = new OrderHistory("티본스테이크-1,초코케이크-1");

        applicableOrder = Order.of(date1, order2);
        nonApplicableOrder1 = Order.of(date2, order2);
        nonApplicableOrder2 = Order.of(date1, order1);
    }

    @Test
    void 이벤트_기간안의_주문인지_확인한다() {
        Date date1 = Date.of(2023, 11, 30); // 평일
        Date date2 = Date.of(2023, 12, 7); // 평일
        OrderHistory menu = new OrderHistory("초코케이크-1");
        Order order1 = Order.of(date1, menu);
        Order order2 = Order.of(date2, menu);

        assertFalse(event.isApplicable(order1));
        assertTrue(event.isApplicable(order2));
    }

    @Test
    void 주문이_조건에_맞는지_확인한다() {
        assertFalse(event.isApplicable(nonApplicableOrder1));
        assertFalse(event.isApplicable(nonApplicableOrder2));
        assertTrue(event.isApplicable(applicableOrder));
    }

    @Test
    void 이벤트명을_반환한다() {
        String expectValue = "평일 할인";

        String actualValue = event.eventName();

        assertEquals(expectValue, actualValue);
    }

    @Test
    void 할인금액을_반환한다() {
        int expectValue = 2023;
        int actualValue = event.discountAmount(applicableOrder);

        assertEquals(expectValue, actualValue);
    }
}
