package christmas.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("DecemberEventBadge 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DecemberEventBadgeTest {

    @ParameterizedTest
    @CsvSource(value = {"20000,산타", "10000,트리", "5000,별", "4999,없음"})
    void 혜택금액에_맞는_뱃지를_반환한다(int amount, String expectValue) {
        String actualValue = DecemberEventBadge.getBadge(amount);

        assertEquals(expectValue, actualValue);
    }
}
