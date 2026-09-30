import java.util.List;

public class FCFS implements Algorithm {
    private List<Task> queue;

    public FCFS(List<Task> queue) {
        this.queue = queue;
    }
    
    @Override
    public void schedule() {
        while (!queue.isEmpty()) {
            Task nextTask = pickNextTask();
            CPU.run(nextTask, nextTask.getBurst());
            queue.remove(nextTask);
        }
    }

    public Task pickNextTask() {
        return queue.get(0);
    }
}