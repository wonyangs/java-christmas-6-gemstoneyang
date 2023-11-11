package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;

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
}
