import java.util.List;

public class Priority implements Algorithm{
    private List<Task> queue;
    private int currentTime = 0;
    private int totalTasks;

    private int totalTurnaround = 0;
    private int totalWaiting = 0;
    private int totalResponse = 0;

    public Priority(List<Task> queue){
        this.queue = queue;
        this.totalTasks = queue.size();
    }
    public void schedule(){
        System.out.println("Priority Scheduling \n");

        while(!queue.isEmpty()){
            Task highestPriority = pickNextTask();

            totalResponse += currentTime;
            totalWaiting += currentTime;

            CPU.run(highestPriority, highestPriority.getBurst());
            currentTime += highestPriority.getBurst();

            totalTurnaround += currentTime;
            queue.remove(highestPriority);
        }
        printAvg();
    }
public Task pickNextTask(){
    Task highestPriority = queue.get(0);

        for(int i = 1; i < queue.size(); i++){
            Task currentTask = queue.get(i);
            if (currentTask.getPriority() > highestPriority.getPriority()){
                highestPriority = currentTask;
            }
        }
        return highestPriority;
    }

    private void printAvg(){
        if (totalTasks == 0){
            System.out.println("No tasks were scheduled");
            return;
        }
        double avgTurnaround = (double) totalTurnaround / totalTasks;
        double avgWaiting = (double) totalWaiting / totalTasks;
        double avgResponse = (double) totalResponse / totalTasks;

        System.out.printf("Average Turnaround Time: %.2f%n",avgTurnaround);
        System.out.printf("Average Waiting Time: %.2f%n", avgWaiting);
        System.out.printf("Average Response Time: %.2f%n", avgResponse);
    }
}
