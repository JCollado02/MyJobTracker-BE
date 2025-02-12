package com.example.myjobtracker.jobapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Contains all resources for API, this is our API layer.
@RestController  // makes class to serve rest endpoints
@RequestMapping(path = "api/v1/jobapp")  // adds to end of localhost link
public class JobAppController {

    private final JobAppService jobAppService;  // references our service

    @Autowired  // tells the controller where to find our service
    public JobAppController(JobAppService jobAppService) {  // passes it to controller
        this.jobAppService = jobAppService;
    }
    @GetMapping
    public List<JobApp> getJobApps() {  // Server returns JSON array
        return jobAppService.getJobApps();
    }
}
