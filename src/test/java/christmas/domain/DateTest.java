package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Date 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DateTest {

    @Test
    void 현재_날짜가_평일인지_확인한다() {
        // 주중인 경우 (예: 2023년 12월 3일 일요일)
        Date date = new Date(2023, 12, 3);
        assertTrue(date.isWeekDays());

        // 주말인 경우 (예: 2023년 12월 8일 금요일)
        date = new Date(2023, 12, 8);
        assertFalse(date.isWeekDays());
    }

    @Test
    void 현재_날짜가_주말인지_확인한다() {
        // 주말인 경우 (예: 2023년 12월 8일 금요일)
        Date date = new Date(2023, 12, 8);
        assertTrue(date.isWeekEnds());

        // 주중인 경우 (예: 2023년 12월 3일 일요일)
        date = new Date(2023, 12, 3);
        assertFalse(date.isWeekEnds());
    }

    @ParameterizedTest
    @CsvSource(value = {"21,11", "10,0", "1,-9"})
    void 현재_날짜와의_차이를_반환한다(int day, int expectValue) {
        Date originDate = new Date(2023, 12, 10);
        Date diffDate = new Date(2023, 12, day);

        int actualValue = originDate.daysBetween(diffDate);

        assertEquals(expectValue, actualValue);
    }
}
