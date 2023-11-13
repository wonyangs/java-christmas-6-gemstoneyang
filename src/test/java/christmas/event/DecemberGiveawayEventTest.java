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

@DisplayName("DecemberGiveawayEvent 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DecemberGiveawayEventTest {
    private static final DecemberGiveawayEvent event = new DecemberGiveawayEvent();
    private static Order nonApplicableOrder;
    private static Order applicableOrder;

    @BeforeAll
    static void setUp() {
        Date date = new Date(2023, 12, 12);
        OrderHistory order1 = new OrderHistory("티본스테이크-1");
        OrderHistory order2 = new OrderHistory("티본스테이크-3");

        nonApplicableOrder = Order.of(date, order1);
        applicableOrder = Order.of(date, order2);
    }

    @Test
    void 총주문금액이_12만원_이상인지_확인한다() {
        assertFalse(event.isApplicable(nonApplicableOrder));
        assertTrue(event.isApplicable(applicableOrder));
    }

    @Test
    void 이벤트명을_반환한다() {
        String expectValue = "증정 이벤트";

        String actualValue = event.eventName();

        assertEquals(expectValue, actualValue);
    }

    @Test
    void 증정메뉴를_반환한다() {
        String expectValue = "샴페인 1개";
        OrderHistory giveaways = event.giveawayMenus(applicableOrder);

        String actualValue = giveaways.toString();

        assertEquals(expectValue, actualValue);
    }
}
