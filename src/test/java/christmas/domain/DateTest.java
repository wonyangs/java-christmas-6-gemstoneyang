package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

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
}
