package com.os.mlq_scheduler.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/process")
    public Map<String, Object> processScheduler(@RequestBody SchedulerRequest schedulerRequest) {
        return schedulerService.runScheduler(schedulerRequest.getProcesses());
    }
}
