package christmas.domain;

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
}
