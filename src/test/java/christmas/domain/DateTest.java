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
        Date date = Date.of(2023, 12, 3);
        assertTrue(date.isWeekDays());

        // 주말인 경우 (예: 2023년 12월 8일 금요일)
        date = Date.of(2023, 12, 8);
        assertFalse(date.isWeekDays());
    }

    @Test
    void 현재_날짜가_주말인지_확인한다() {
        // 주말인 경우 (예: 2023년 12월 8일 금요일)
        Date date = Date.of(2023, 12, 8);
        assertTrue(date.isWeekEnds());

        // 주중인 경우 (예: 2023년 12월 3일 일요일)
        date = Date.of(2023, 12, 3);
        assertFalse(date.isWeekEnds());
    }

    @ParameterizedTest
    @CsvSource(value = {"21,11", "10,0", "1,9"})
    void 현재_날짜와의_차이를_반환한다(int day, int expectValue) {
        Date originDate = Date.of(2023, 12, 10);
        Date diffDate = Date.of(2023, 12, day);

        int actualValue = originDate.daysBetween(diffDate);

        assertEquals(expectValue, actualValue);
    }

    @Test
    void 날짜_범위_안에_속하는지_확인한다() {
        Date startDate = Date.of(2023, 12, 1);
        Date endDate = Date.of(2023, 12, 10);

        Date containDate = Date.of(2023, 12, 7);
        Date nonContainDate = Date.of(2023, 12, 15);

        assertTrue(containDate.isInRange(startDate, endDate));
        assertFalse(nonContainDate.isInRange(startDate, endDate));
    }
}
