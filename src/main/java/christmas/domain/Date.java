package christmas.domain;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Objects;

/**
 * 날짜 정보를 관리하는 클래스
 */
public class Date {
    private static final EnumSet<DayOfWeek> WEEKENDS = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    private final LocalDate date;
    private final DayOfWeek dayOfWeek;

    private Date(int year, int month, int day) {
        // todo: 예외 처리
        this.date = LocalDate.of(year, month, day);
        this.dayOfWeek = date.getDayOfWeek();
    }

    public static Date of(int year, int month, int day) {
        return new Date(year, month, day);
    }

    public boolean isWeekDays() {
        return !WEEKENDS.contains(dayOfWeek);
    }

    public boolean isWeekEnds() {
        return WEEKENDS.contains(dayOfWeek);
    }

    public int daysBetween(Date other) {
        int diff = (int) DAYS.between(this.date, other.date);
        return Math.abs(diff);
    }

    public boolean isInRange(Date start, Date end) {
        return (date.isAfter(start.date) || date.isEqual(start.date)) &&
                (date.isBefore(end.date) || date.isEqual(end.date));
    }

    @Override
    public String toString() {
        return String.format("%d월 %d일", date.getMonthValue(), date.getDayOfMonth());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date1 = (Date) o;
        return date.equals(date1.date) && dayOfWeek == date1.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, dayOfWeek);
    }
}
