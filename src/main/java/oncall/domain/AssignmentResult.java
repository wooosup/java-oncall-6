
package oncall.domain;

import java.time.DayOfWeek;

public record AssignmentResult(
        int month,
        int day,
        DayOfWeek dayOfWeek,
        boolean isHoliday,
        String workerName
) {
    @Override
    public String toString() {
        String dayString = month + "월 " + day + "일 " + dayOfWeekToString(dayOfWeek);
        if (isHoliday) {
            dayString += "(휴일)";
        }
        return dayString + " " + workerName;
    }

    private String dayOfWeekToString(DayOfWeek day) {
        String[] koreanDays = {"월", "화", "수", "목", "금", "토", "일"};
        return koreanDays[day.getValue() - 1];
    }
}