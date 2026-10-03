import java.util.*;
 
public class PriorityRR implements Algorithm
{
    public static final int QUANTUM = 10;
 
    // priority -> queue of tasks at that priority, highest priority first
    private TreeMap<Integer, Deque<Task>> levels =
            new TreeMap<Integer, Deque<Task>>(Comparator.reverseOrder());
 
    private int currentTime = 0;
    private int numTasks;
 
    // setBurst() changes the task's burst, so save the original (keyed by tid)
    private Map<Integer, Integer> originalBurst = new HashMap<Integer, Integer>();
    private Set<Integer> started = new HashSet<Integer>();
 
    private int totalTurnaround = 0;
    private int totalWaiting = 0;
    private int totalResponse = 0;
 
    public PriorityRR(List<Task> queue) {
        this.numTasks = queue.size();
 
        for (Task t : queue) {
            originalBurst.put(t.getTid(), t.getBurst());
 
            // put each task in the queue for its priority (file order kept)
            if (!levels.containsKey(t.getPriority()))
                levels.put(t.getPriority(), new ArrayDeque<Task>());
            levels.get(t.getPriority()).addLast(t);
        }
    }
 
    public void schedule() {
        System.out.println("Priority with RR Scheduling \n");
 
        while (!levels.isEmpty()) {
            Task t = pickNextTask();
            Deque<Task> level = levels.get(t.getPriority());
 
            // alone at this level -> run to completion; otherwise one quantum
            int slice;
            if (level.isEmpty())
                slice = t.getBurst();
            else
                slice = Math.min(QUANTUM, t.getBurst());
 
            // response time: the first time this task gets the CPU
            if (started.add(t.getTid()))
                totalResponse += currentTime;
 
            CPU.run(t, slice);
            currentTime += slice;
            t.setBurst(t.getBurst() - slice);
 
            if (t.getBurst() > 0) {
                level.addLast(t);                         // back of its own level
            } else {
                int turnaround = currentTime;             // arrival time is 0
                totalTurnaround += turnaround;
                totalWaiting += turnaround - originalBurst.get(t.getTid());
                System.out.println("Task " + t.getName() + " finished.\n");
 
                if (level.isEmpty())                      // level done, move down
                    levels.remove(t.getPriority());
            }
        }
 
        printAverages();
    }
 
    /** The front task of the highest-priority level that still has tasks. */
    public Task pickNextTask() {
        return levels.firstEntry().getValue().pollFirst();
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