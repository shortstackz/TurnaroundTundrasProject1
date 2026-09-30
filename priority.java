import java.util.List;

public class priority implements Algorithm{
    private List<Task> queue;

    public priority(List<Task> queue){
        this.queue = queue;
    }
    public void schedule(){
        while(!queue.isEmpty()){
            Task highestPriority = pickNextTask();
            CPU.run(highestPriority, highestPriority.getBurst());
            queue.remove(highestPriority);
        }
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
}
