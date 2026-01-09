package oncall.controller;

import java.util.List;
import java.util.function.Supplier;
import oncall.domain.AssignmentResult;
import oncall.domain.WorkMonth;
import oncall.domain.WorkerQueue;
import oncall.service.OncallService;
import oncall.util.Parser;
import oncall.util.Validator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final OncallService service = new OncallService();

    public void run() {
        WorkMonth workMonth = getWorkMonth();
        List<WorkerQueue> queues = getWorkerQueues();

        List<AssignmentResult> results = service.assign(
                workMonth, queues.get(0), queues.get(1)
        );

        outputView.printResults(results);
    }

    private WorkMonth getWorkMonth() {
        return retry(() -> {
            String input = inputView.readMonthAndDay();
            String[] tokens = input.split(",");
            if (tokens.length != 2) {
                throw new IllegalArgumentException("[ERROR] 입력 형식이 올바르지 않습니다.");
            }
            int month = Parser.parseMonth(tokens[0]);
            Validator.validateMonth(month);
            return new WorkMonth(month, Parser.parseDayOfWeek(tokens[1]));
        });
    }

    private List<WorkerQueue> getWorkerQueues() {
        return retry(() -> {
            List<String> weekdayNames = Parser.parseWorkers(inputView.readWeekdayWorkers());
            List<String> holidayNames = Parser.parseWorkers(inputView.readHolidayWorkers());

            return List.of(
                    new WorkerQueue(weekdayNames),
                    new WorkerQueue(holidayNames)
            );
        });
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}