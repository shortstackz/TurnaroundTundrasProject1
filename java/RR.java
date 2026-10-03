import java.util.*;

public class RR implements Algorithm {
    public static final int QUANTUM = 10;

    private Deque<Task> readyQueue;
    private int currentTime = 0;
    private int numTasks;

    //setBurst() to change the task's burst
    private Map<Integer, Integer> originalBurst = new HashMap<Integer, Integer>();
    private Set<Integer> started = new HashSet<Integer>();

    private int totalTurnaround= 0;
    private int totalWaiting = 0;
    private int totalResponse = 0;

    public RR(List<Task> queue) {
        this.readyQueue = new ArrayDeque<Task>(queue);
        this.numTasks = queue.size();

        for (Task t : queue)
            originalBurst.put(t.getTid(), t.getBurst());
    }

    public void schedule() {
        System.out.println("RR Scheduling \n");

        while (!readyQueue.isEmpty()) {
            Task t = pickNextTask();
            int slice = Math.min(QUANTUM, t.getBurst());

            //response time
            if (started.add(t.getTid()))
                totalResponse += currentTime;

            CPU.run(t, slice);
            currentTime += slice;
            t.setBurst(t.getBurst() - slice);

            if (t.getBurst() > 0) {
                readyQueue.addLast(t);  //end of queue
            } else {
                int turnaround = currentTime;     // arrival time is 0
                totalTurnaround += turnaround;
                totalWaiting += turnaround - originalBurst.get(t.getTid());
                System.out.println("Task " + t.getName() + " finished.\n");
            }
        }
        printAverages();
    }
    //task at the front of the queue
    public Task pickNextTask() {
        return readyQueue.pollFirst();
    }
        private void printAverages() {
        if (numTasks == 0) {
            System.out.println("No tasks were scheduled.");
            return;
        }
        System.out.printf("Average Turnaround Time: %.2f%n", (double) totalTurnaround / numTasks);
        System.out.printf("Average Waiting Time:    %.2f%n", (double) totalWaiting / numTasks);
        System.out.printf("Average Response Time:   %.2f%n", (double) totalResponse / numTasks);
    }
}