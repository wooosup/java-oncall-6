package oncall.view;

import java.util.List;
import oncall.domain.AssignmentResult;

public class OutputView {

    public void printError(String message) {
        System.out.println(message);
    }

    public void printResults(List<AssignmentResult> results) {
        System.out.println();
        for (AssignmentResult result : results) {
            System.out.println(result.toString());
        }
    }
}
