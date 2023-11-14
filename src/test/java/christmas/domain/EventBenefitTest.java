package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("EventBenefit 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EventBenefitTest {
    private EventBenefit benefit;

    @BeforeEach
    void setUp() {
        benefit = new EventBenefit();
        benefit.addDiscountBenefit("크리스마스 디데이 할인", 1200);
        benefit.addDiscountBenefit("평일 할인", 4046);
        benefit.addDiscountBenefit("특별 할인", 1000);

        OrderHistory giveaway = new OrderHistory("샴페인-1");
        benefit.addGiveawayBenefit("증정 이벤트", giveaway);
    }

    @Test
    void 총혜택_금액을_반환한다() {
        int actualValue = benefit.totalBenefitAmount();
        int expectValue = 31246;

        assertEquals(expectValue, actualValue);
    }

    @Test
    void 총할인_금액을_반환한다() {
        int actualValue = benefit.totalDiscountAmount();
        int expectValue = 6246;

        assertEquals(expectValue, actualValue);
    }
}
