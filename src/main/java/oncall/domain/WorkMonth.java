package oncall.domain;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;

public class WorkMonth {
    private static final Set<MonthDay> HOLIDAYS = Set.of(
            MonthDay.of(1, 1), MonthDay.of(3, 1), MonthDay.of(5, 5),
            MonthDay.of(6, 6), MonthDay.of(8, 15), MonthDay.of(10, 3),
            MonthDay.of(10, 9), MonthDay.of(12, 25)
    );
    private static final List<Integer> THIRTY_DAYS = List.of(4, 6, 9, 11);

    private final int month;
    private final DayOfWeek startDayOfWeek;

    public WorkMonth(int month, DayOfWeek startDayOfWeek) {
        this.month = month;
        this.startDayOfWeek = startDayOfWeek;
    }

    public int getLastDay() {
        if (month == 2) {
            return 28;
        }
        if (THIRTY_DAYS.contains(month)) {
            return 30;
        }
        return 31;
    }

    public boolean isHoliday(int day) {
        DayOfWeek currentDay = calculateDay(day);
        if (isWeekend(currentDay)) {
            return true;
        }
        return isLegalHoliday(day);
    }

    public boolean isWeekdayLegalHoliday(int day) {
        DayOfWeek currentDay = calculateDay(day);
        if (isWeekend(currentDay)) {
            return false;
        }
        return isLegalHoliday(day);
    }

    public DayOfWeek calculateDay(int day) {
        return startDayOfWeek.plus(day - 1);
    }

    private boolean isWeekend(DayOfWeek day) {
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private boolean isLegalHoliday(int day) {
        return HOLIDAYS.contains(MonthDay.of(month, day));
    }

    public int getMonth() {
        return month;
    }
}