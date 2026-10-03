import java.util.List;

public class SJF implements Algorithm {
    private List<Task> queue;
    private int taskCount;

    public SJF(List<Task> queue) {
        this.queue = queue;
        // Record total number of tasks before removing them in schedule()
        this.taskCount = queue.size();
    }

    @Override
    public void schedule() {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;

        while (!queue.isEmpty()) {
            Task task = pickNextTask();

            // Task starts executing at currentTime
            int waitingTime = currentTime;
            int responseTime = currentTime; 
            int burst = task.getBurst();
            int turnaroundTime = waitingTime + burst;

            totalWaitingTime += waitingTime;
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;

            // Run task and advance timeline
            CPU.run(task, burst);
            currentTime += burst;

            queue.remove(task);
        }

        // Calculate and print the averages
        if (taskCount > 0) {
            double avgWaiting = (double) totalWaitingTime / taskCount;
            double avgTurnaround = (double) totalTurnaroundTime / taskCount;
            double avgResponse = (double) totalResponseTime / taskCount;

            System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaround);
            System.out.printf("Average Waiting Time:    %.2f\n", avgWaiting);
            System.out.printf("Average Response Time:   %.2f\n", avgResponse);
        }
    }

    @Override
    public Task pickNextTask() {
        Task shortest = queue.get(0);
        for (Task task : queue) {
            if (task.getBurst() < shortest.getBurst()) {
                shortest = task;
            }
        }
        return shortest;
    }
}



