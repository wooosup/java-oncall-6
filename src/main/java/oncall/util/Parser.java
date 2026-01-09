
package oncall.util;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    public static int parseMonth(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 월은 숫자여야 합니다.");
        }
    }

    public static DayOfWeek parseDayOfWeek(String input) {
        String[] days = {"월", "화", "수", "목", "금", "토", "일"};
        for (int i = 0; i < days.length; i++) {
            if (days[i].equals(input.trim())) {
                return DayOfWeek.of(i + 1);
            }
        }
        throw new IllegalArgumentException("[ERROR] 올바르지 않은 요일입니다.");
    }

    public static List<String> parseWorkers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }
}