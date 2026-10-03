import java.util.List;

public class FCFS implements Algorithm {
    private List<Task> queue;
    
    public FCFS(List<Task> queue) {
        this.queue = queue;
    }
    
    @Override
    public void schedule() {
        // Variables for calculating the different times
        int currentTime = 0;
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int numTask = queue.size();
        
        while (!queue.isEmpty()) {
            Task nextTask = pickNextTask();
            int burst = nextTask.getBurst();

            int responseTime = currentTime;
            int waitTime = currentTime;
            int turnaroundTime = currentTime + burst;

            totalResponseTime += responseTime;
            totalWaitTime += waitTime;
            totalTurnaroundTime += turnaroundTime;
            currentTime += burst;
            CPU.run(nextTask, nextTask.getBurst());
            queue.remove(nextTask);

        }
        if (numTask > 0) {
            System.out.printf("\n--- Performance Metrics ---\n");
            System.out.printf("Average Turnaround Time: %.2f\n", (double) totalTurnaroundTime / numTask);
            System.out.printf("Average Waiting Time:    %.2f\n", (double) totalWaitTime / numTask);
            System.out.printf("Average Response Time:   %.2f\n", (double) totalResponseTime / numTask);
        }
    }

    public Task pickNextTask() {
        return queue.get(0);
    }
}
