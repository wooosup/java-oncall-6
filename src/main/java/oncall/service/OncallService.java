package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.AssignmentResult;
import oncall.domain.WorkMonth;
import oncall.domain.WorkerQueue;

public class OncallService {

    public List<AssignmentResult> assign(WorkMonth workMonth, WorkerQueue weekday, WorkerQueue holiday) {
        List<AssignmentResult> results = new ArrayList<>();
        String prev = "";

        for (int day = 1; day <= workMonth.getLastDay(); day++) {
            boolean isHoliday = workMonth.isHoliday(day);
            WorkerQueue current = selectQueue(isHoliday, weekday, holiday);

            String worker = determineWorker(current, prev);

            results.add(createResult(workMonth, day, worker));
            prev = worker;
        }
        return results;
    }

    private WorkerQueue selectQueue(boolean isHoliday, WorkerQueue weekday, WorkerQueue holiday) {
        if (isHoliday) {
            return holiday;
        }
        return weekday;
    }

    private String determineWorker(WorkerQueue queue, String previousWorker) {
        String candidate = queue.peek();
        if (candidate.equals(previousWorker)) {
            queue.swap();
            candidate = queue.peek();
        }
        queue.next();
        return candidate;
    }

    private AssignmentResult createResult(WorkMonth workMonth, int day, String worker) {
        return new AssignmentResult(
                workMonth.getMonth(),
                day,
                workMonth.calculateDay(day),
                workMonth.isWeekdayLegalHoliday(day),
                worker
        );
    }
}