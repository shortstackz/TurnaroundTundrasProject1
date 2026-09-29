# CS3600-001-F26-Project-1-Scheduling-Algorithms
# Project Overview

In this project, you will implement and evaluate several CPU process scheduling algorithms. The scheduler will receive a predefined set of tasks, each with an assigned priority and CPU burst time, and determine the order in which the tasks are executed based on the selected scheduling algorithm.

You will implement the following five scheduling algorithms:

## First-Come, First-Served (FCFS)
Executes tasks in the order in which they request access to the CPU.

## Shortest-Job-First (SJF)
Executes tasks in ascending order based on the length of their next CPU burst.

## Priority Scheduling
Executes tasks according to their assigned priority.

## Round-Robin (RR)
Executes each task for a specified time quantum. If the remaining CPU burst is shorter than the time quantum, the task runs for its remaining burst time.

## Priority Scheduling with Round-Robin
Executes tasks according to priority. Tasks with the same priority are scheduled using the Round-Robin algorithm.

Task priorities range from 1 to 10, where a higher numeric value represents a higher priority. For Round-Robin scheduling, use a time quantum of 10 milliseconds.

# I. Implementation Requirements

The project may be implemented in either C or Java. Supporting source files for both languages are provided with the project materials. These files are responsible for reading the task schedule, storing the tasks in an appropriate data structure, and invoking the selected scheduling algorithm.

Each task in the input file follows this format:

[Task Name], [Priority], [CPU Burst]

For example:

T1, 4, 20

T2, 2, 25

T3, 3, 25

T4, 3, 15

T5, 10, 10


For example, T1 has a priority of 4 and requires a CPU burst of 20 milliseconds.

For this project, assume that all tasks arrive at the same time. Therefore, your implementation does not need to support preemption in which a newly arriving higher-priority task interrupts a currently executing lower-priority task.

Tasks do not need to be initially stored in any particular order.

## Organizing Tasks

There are several acceptable approaches for organizing the tasks. You may, for example:

Store all tasks in a single unordered list and search the list according to the scheduling algorithm.

Maintain a list ordered according to scheduling criteria, such as priority.

Maintain separate queues for different priority levels.

Remember that a queue specifically follows FIFO behavior, whereas a general list provides more flexibility in insertion, deletion, and selection. Depending on your implementation, a general list may therefore be more appropriate.

# II. C Implementation

The provided driver.c file reads the task schedule, inserts each task into a linked list, and invokes the scheduler through the schedule() function.

The schedule() function is responsible for executing tasks according to the selected scheduling algorithm. The next task to execute is determined by the pickNextTask() function. Once selected, the task is executed by calling the run() function provided in CPU.c.

A Makefile is provided to build the appropriate scheduling implementation.

For example, to compile the FCFS scheduler:

make fcfs


To execute the scheduler using schedule.txt:

./fcfs schedule.txt


Before beginning your implementation, carefully review the provided source files and the Makefile to understand the structure of the starter code.

*Completing this project will require writing the following C files:*

***schedule_fcfs.c***

***schedule_sjf.c***

***schedule_rr.c***

***schedule_priority.c***

***schedule_priority_rr.c***

*The supporting files invoke the appropriate scheduling algorithm.*

*For example, to build the FCFS scheduler, enter*

*make fcfs*

*which builds the fcfs executable file.*


# III. Java Implementation

The provided Driver.java file reads the task schedule, stores the tasks in a Java ArrayList, and invokes the appropriate scheduling algorithm through the schedule() method.

Each of the five scheduling algorithms must implement the provided Algorithm interface:

public interface Algorithm {

    // Implements the scheduling algorithm
    public void schedule();

    // Selects the next task to be scheduled
    public Task pickNextTask();
}


The schedule() method should repeatedly determine the next task to execute by calling pickNextTask(). The selected task is then executed by calling the static run() method provided in CPU.java.

To run the Java implementation, use the following format:

java Driver fcfs schedule.txt


Before beginning your implementation, carefully review the provided Java source files.

*Completing this project will require writing the following Java classes:*

***FCFS.java*** 

***SJF.java*** 

***RR.java*** 

***Priority.java*** 

***PriorityRR.java*** 

*Each of these classes must implement the Algorithm.java interface.*

# IV. Additional Requirements
1. Calculate Scheduling Performance Metrics

For each scheduling algorithm, calculate and report the following average performance metrics:

Average Turnaround Time

Average Waiting Time

Average Response Time

Your calculations should be based on the execution order produced by each scheduling algorithm.

# Expected Outcome

By completing this project, you should be able to:

Implement multiple CPU scheduling algorithms.

Compare different scheduling strategies.

Work with task priorities and CPU burst times.

Apply Round-Robin scheduling using a fixed time quantum.

Calculate and analyze turnaround time, waiting time, and response time for CPU scheduling algorithms.

# Getting Started and Submitting Your Work

To get started, clone the provided repository and complete the work locally on your own computer. Throughout the development process, you are encouraged to make incremental changes and document your progress by creating commits in your local Git repository.

When you are ready to submit your work, create a Git bundle containing your repository history using the following command:

```bash git bundle create <team_name>.bundle --all```

Replace <team_name> with your own team names. For example, if I were creating a bundle for my repository, I would use:

```bash git bundle create team_awesome.bundle --all```

It's important to use the <team_name> format as it helps identifying you for grading purposes.

Next, submit your bundle file on Canvas before the deadline.
