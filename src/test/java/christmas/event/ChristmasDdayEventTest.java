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

@DisplayName("ChristmasDdayEvent 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ChristmasDdayEventTest {
    private static final ChristmasDdayEvent event = new ChristmasDdayEvent();
    private static Order nonApplicableOrder;
    private static Order applicableOrder;

    @BeforeAll
    static void setUp() {
        Date date1 = Date.of(2023, 12, 1);
        Date date2 = Date.of(2023, 12, 28);
        OrderHistory order = new OrderHistory("티본스테이크-1");

        applicableOrder = Order.of(date1, order);
        nonApplicableOrder = Order.of(date2, order);
    }

    @Test
    void 주문이_이벤트_기간에_포함되는지_확인한다() {
        assertFalse(event.isApplicable(nonApplicableOrder));
        assertTrue(event.isApplicable(applicableOrder));
    }

    @Test
    void 이벤트명을_반환한다() {
        String expectValue = "크리스마스 디데이 할인";

        String actualValue = event.eventName();

        assertEquals(expectValue, actualValue);
    }

    @Test
    void 할인금액을_반환한다() {
        int expectValue = 1000;
        int actualValue = event.discountAmount(applicableOrder);

        assertEquals(expectValue, actualValue);
    }
}
