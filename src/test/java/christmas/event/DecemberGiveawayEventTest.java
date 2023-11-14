package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.config.Menu;
import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import java.util.Map;
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
        Date date = Date.of(2023, 12, 12);
        OrderHistory order1 = new OrderHistory("티본스테이크-1");
        OrderHistory order2 = new OrderHistory("티본스테이크-3");

        nonApplicableOrder = Order.of(date, order1);
        applicableOrder = Order.of(date, order2);
    }

    @Test
    void 이벤트_기간안의_주문인지_확인한다() {
        Date date1 = Date.of(2023, 11, 30); // 평일
        Date date2 = Date.of(2023, 12, 7); // 평일
        OrderHistory menu = new OrderHistory("티본스테이크-3,초코케이크-1");
        Order order1 = Order.of(date1, menu);
        Order order2 = Order.of(date2, menu);

        assertFalse(event.isApplicable(order1));
        assertTrue(event.isApplicable(order2));
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
        Map<Menu, Integer> giveaways = event.giveawayMenus(applicableOrder);
        OrderHistory giveawayMenus = new OrderHistory(giveaways);

        String actualValue = giveawayMenus.toString();

        assertEquals(expectValue, actualValue);
    }

    @Test
    void 주문금액이_부족한_경우_없음을_반환한다() {
        Date date = Date.of(2023, 12, 26);
        OrderHistory menu = new OrderHistory("타파스-1,제로콜라-1");
        Order order = Order.of(date, menu);

        OrderHistory giveawayMenus =  new OrderHistory(event.giveawayMenus(order));

        String actualValue = giveawayMenus.toString();
        String expectValue = "없음";

        assertEquals(expectValue, actualValue);
    }
}
