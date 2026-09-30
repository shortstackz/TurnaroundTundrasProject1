import java.util.List;

public class SJF implements Algorithm{
  private List<Task> queue;

  public SJF(List<Task> queue) {
    this.queue = queue;
    }

@Override
  public void schedule() {
    while (!queue.isEmpty()) {
      Task task = pickNextTask();
      CPU.run(task, task.getBurst());
      queue.remove(task);     
    }
  }
  
  @Override
  public Task pickNextTask() {
    Task shortest = queue.get(0);
    for (Task task : queue) {
      if (task.getBurst() < shortest.getBurst()){
        shortest = task;
      }
    }
    return shortest;
  }
}

