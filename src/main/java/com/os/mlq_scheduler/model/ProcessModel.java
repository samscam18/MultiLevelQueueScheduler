package com.os.mlq_scheduler.model;


public class ProcessModel {
    private String pid;
    private int arrivalTime;
    private int burstTime;
    private QueueType queueType;

    // Constructor
    public ProcessModel() {
    }

    public ProcessModel(String pid, int arrivalTime, int burstTime, QueueType queueType) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.queueType = queueType;
    }

    // Getters and Setters
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public QueueType getQueueType() {
        return queueType;
    }

    public void setQueueType(QueueType queueType) {
        this.queueType = queueType;
    }

    @Override
    public String toString() {
        return String.format("PID: %s | Arrival: %d | Burst: %d | Queue: %s", 
            pid, arrivalTime, burstTime, queueType);
    }
}
