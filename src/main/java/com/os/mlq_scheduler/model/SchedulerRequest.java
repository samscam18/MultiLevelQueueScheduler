package com.os.mlq_scheduler.model;

import java.util.List;

public class SchedulerRequest {
    private List<ProcessModel> processes; // Fixed typo in variable name

    public SchedulerRequest() { // Fixed constructor name
    }

    public SchedulerRequest(List<ProcessModel> processes) { // Fixed typo in parameter name
        this.processes = processes; // Fixed typo in variable name
    }

    public List<ProcessModel> getProcesses() {
        return processes;
    }

    public void setProcesses(List<ProcessModel> processes) {
        this.processes = processes;
    }
}