package christmas.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Validator 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "25"})
    void 올바른_날짜입력_문자열을_검증한다(String input) {
        boolean actualValue = Validator.isValidDay(input);

        assertTrue(actualValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-13", "0", " ", "", "a1", "32", "123", " 3"})
    void 잘못된_날짜입력_문자열을_검증한다(String input) {
        boolean actualValue = Validator.isValidDay(input);

        assertFalse(actualValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc--1", ",abc-1", "-1", "abc-a"})
    void 잘못된_주문_입력형식을_검증한다(String input) {
        boolean actualValue = Validator.isValidOrder(input);

        assertFalse(actualValue);
    }
}
