package christmas.domain;

import static christmas.config.Menu.BBQ_RIBS;
import static christmas.config.Menu.CHOCOLATE_CAKE;
import static christmas.config.Menu.T_BONE_STEAK;
import static christmas.config.Menu.ZERO_COLA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("OrderHistory 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OrderHistoryTest {

    @Test
    void 사용자의_올바른_입력을_객체로_만든다() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        OrderHistory orderHistory = new OrderHistory(input);

        String result = orderHistory.toString();

        assertTrue(result.contains("티본스테이크 1개"));
        assertTrue(result.contains("바비큐립 1개"));
        assertTrue(result.contains("초코케이크 2개"));
        assertTrue(result.contains("제로콜라 1개"));
    }

    @Test
    void 주문의_총금액을_반환한다() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        OrderHistory orderHistory = new OrderHistory(input);

        int expect = T_BONE_STEAK.getPrice() * 1
                + BBQ_RIBS.getPrice() * 1
                + CHOCOLATE_CAKE.getPrice() * 2
                + ZERO_COLA.getPrice() * 1;

        int actual = orderHistory.totalPrice();

        assertEquals(expect, actual);
    }

    @Test
    void 주문이_없을때_총금액을_반환한다() {
        String input = "";

        OrderHistory orderHistory = new OrderHistory(input);

        int expect = 0;
        int actual = orderHistory.totalPrice();

        assertEquals(expect, actual);
    }
}
