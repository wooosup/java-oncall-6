package oncall.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oncall.util.Validator;

public class WorkerQueue {
    private final List<String> workers;
    private int index = 0;

    public WorkerQueue(List<String> workers) {
        Validator.validateWorkerNames(workers);
        this.workers = new ArrayList<>(workers);
    }

    public String peek() {
        return workers.get(index % workers.size());
    }

    public void next() {
        index++;
    }

    public void swap() {
        int current = index % workers.size();
        int next = (index + 1) % workers.size();
        Collections.swap(workers, current, next);
    }
}