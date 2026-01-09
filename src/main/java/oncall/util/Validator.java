package oncall.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    public static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("[ERROR] 월은 1에서 12 사이여야 합니다.");
        }
    }

    public static void validateWorkerNames(List<String> workers) {
        if (workers.size() < 5 || workers.size() > 35) {
            throw new IllegalArgumentException("[ERROR] 근무자는 5명 이상 35명 이하여야 합니다.");
        }
        validateDuplicate(workers);
        for (String worker : workers) {
            validateNameLength(worker);
        }
    }

    private static void validateDuplicate(List<String> workers) {
        Set<String> uniqueWorkers = new HashSet<>(workers);
        if (uniqueWorkers.size() != workers.size()) {
            throw new IllegalArgumentException("[ERROR] 근무자는 중복될 수 없습니다.");
        }
    }

    private static void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 닉네임은 1글자 이상 5글자 이하여야 합니다.");
        }
    }
}