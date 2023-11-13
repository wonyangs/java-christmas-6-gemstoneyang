package christmas.domain;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;

/**
 * 날짜 정보를 관리하는 클래스
 */
public class Date {
    private static final EnumSet<DayOfWeek> WEEKENDS = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    private final LocalDate date;
    private final DayOfWeek dayOfWeek;

    public Date(int year, int month, int day) {
        // todo: 예외 처리
        this.date = LocalDate.of(year, month, day);
        this.dayOfWeek = date.getDayOfWeek();
    }

    public boolean isWeekDays() {
        return !WEEKENDS.contains(dayOfWeek);
    }

    public boolean isWeekEnds() {
        return WEEKENDS.contains(dayOfWeek);
    }

    public int daysBetween(Date other) {
        return (int) DAYS.between(this.date, other.date);
    }
    
    public boolean isInRange(Date start, Date end) {
        return (date.isAfter(start.date) || date.isEqual(start.date)) &&
                (date.isBefore(end.date) || date.isEqual(end.date));
    }
}
