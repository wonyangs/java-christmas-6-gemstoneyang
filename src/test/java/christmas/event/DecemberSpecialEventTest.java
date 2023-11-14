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

@DisplayName("DecemberSpecialEvent 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DecemberSpecialEventTest {

    private static final DecemberSpecialEvent event = new DecemberSpecialEvent();
    private static Order nonApplicableOrder;
    private static Order applicableOrder;

    @BeforeAll
    static void setUp() {
        Date date1 = Date.of(2023, 12, 3); // 별 O
        Date date2 = Date.of(2023, 12, 23); // 별 X
        OrderHistory menu = new OrderHistory("티본스테이크-2");

        applicableOrder = Order.of(date1, menu);
        nonApplicableOrder = Order.of(date2, menu);
    }

    @Test
    void 주문이_조건에_맞는지_확인한다() {
        assertTrue(event.isApplicable(applicableOrder));
        assertFalse(event.isApplicable(nonApplicableOrder));
    }

    @Test
    void 이벤트명을_반환한다() {
        String expectValue = "특별 할인";

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
