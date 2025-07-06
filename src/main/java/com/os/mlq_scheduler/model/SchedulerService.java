package com.os.mlq_scheduler.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    public Map<String, Object> runScheduler(List<ProcessModel> processes) {
        List<ProcessModel> systemQueue = new ArrayList<>();
        List<ProcessModel> interactiveQueue = new ArrayList<>();
        List<ProcessModel> batchQueue = new ArrayList<>();

        for (ProcessModel p : processes) {
            switch (p.getQueueType()) {
                case SYSTEM -> systemQueue.add(p);
                case INTERACTIVE -> interactiveQueue.add(p);
                case BATCH -> batchQueue.add(p);
            }
        }

        List<String> executionOrder = new ArrayList<>();
        Map<String, Integer> waitingTimes = new HashMap<>();
        Map<String, Integer> turnaroundTimes = new HashMap<>();

        int currentTime = 0;

        // Priority Order: SYSTEM > INTERACTIVE > BATCH

        // SYSTEM → Round Robin (quantum = 2)
        currentTime = runRoundRobin(systemQueue, currentTime, 2, executionOrder, waitingTimes, turnaroundTimes);

        // INTERACTIVE → FCFS
        currentTime = runFCFS(interactiveQueue, currentTime, executionOrder, waitingTimes, turnaroundTimes);

        // BATCH → FCFS
        currentTime = runFCFS(batchQueue, currentTime, executionOrder, waitingTimes, turnaroundTimes);

        // Build result map
        Map<String, Object> result = new HashMap<>();
        result.put("executionOrder", executionOrder);
        result.put("waitingTimes", waitingTimes);
        result.put("turnaroundTimes", turnaroundTimes);

        return result;
    }

    private int runRoundRobin(List<ProcessModel> queue, int currentTime, int quantum,
                              List<String> executionOrder,
                              Map<String, Integer> waitingTimes,
                              Map<String, Integer> turnaroundTimes) {
        if (queue.isEmpty()) return currentTime;

        Queue<ProcessModel> rrQueue = new LinkedList<>(queue);
        Map<String, Integer> remainingBurst = new HashMap<>();
        Map<String, Integer> arrivalMap = new HashMap<>();

        for (ProcessModel p : queue) {
            remainingBurst.put(p.getPid(), p.getBurstTime());
            arrivalMap.put(p.getPid(), p.getArrivalTime());
        }

        while (!rrQueue.isEmpty()) {
            ProcessModel p = rrQueue.poll();

            if (arrivalMap.get(p.getPid()) > currentTime) {
                currentTime = arrivalMap.get(p.getPid());
            }

            int runTime = Math.min(quantum, remainingBurst.get(p.getPid()));
            executionOrder.add(p.getPid() + " (" + runTime + " units)");
            currentTime += runTime;

            remainingBurst.put(p.getPid(), remainingBurst.get(p.getPid()) - runTime);

            if (remainingBurst.get(p.getPid()) > 0) {
                rrQueue.offer(p);  // requeue
            } else {
                int wt = currentTime - p.getArrivalTime() - p.getBurstTime();
                int tat = currentTime - p.getArrivalTime();
                waitingTimes.put(p.getPid(), wt);
                turnaroundTimes.put(p.getPid(), tat);
            }
        }

        return currentTime;
    }

    private int runFCFS(List<ProcessModel> queue, int currentTime,
                        List<String> executionOrder,
                        Map<String, Integer> waitingTimes,
                        Map<String, Integer> turnaroundTimes) {
        if (queue.isEmpty()) return currentTime;

        queue.sort(Comparator.comparingInt(ProcessModel::getArrivalTime));

        for (ProcessModel p : queue) {
            if (p.getArrivalTime() > currentTime) {
                currentTime = p.getArrivalTime();
            }

            executionOrder.add(p.getPid() + " (" + p.getBurstTime() + " units)");
            int wt = currentTime - p.getArrivalTime();
            int tat = wt + p.getBurstTime();

            waitingTimes.put(p.getPid(), wt);
            turnaroundTimes.put(p.getPid(), tat);

            currentTime += p.getBurstTime();
        }

        return currentTime;
    }
}
