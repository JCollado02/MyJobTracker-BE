package com.example.myjobtracker.jobapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Contains all resources for API, this is our API layer.
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React frontend
@RestController  // makes class to serve rest endpoints
@RequestMapping(path = "api/v1/job-applications")  // adds to end of localhost link
public class JobAppController {

    private final JobAppService jobAppService;  // references our service

    @Autowired  // tells the controller where to find our service
    public JobAppController(JobAppService jobAppService) {  // passes it to controller
        this.jobAppService = jobAppService;
    }
    @GetMapping
    public List<JobApp> getJobApps() {  // Server returns JSON array
        return jobAppService.getAllJobApps();
    }

    @GetMapping("{id}")
    public JobApp getJobAppsById(@PathVariable Integer id) {  // Finds job by given id
        return jobAppService.getJobAppById(id);
    }

    @PostMapping
    public ResponseEntity<JobApp> addNewJobApp(@RequestBody JobApp jobApp) {  // inserts new job
        jobAppService.insertJobApp(jobApp);
        return ResponseEntity.ok(jobApp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteJobApp(@PathVariable Integer id) {  // deletes a specified job
        jobAppService.deleteJobApp(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<JobApp> updateJobApp(@PathVariable Integer id, @RequestBody JobApp jobAppDetails) {
        JobApp updatedJobApp = jobAppService.updateJobApp(id, jobAppDetails);
        return ResponseEntity.ok(updatedJobApp);
    }

    @PatchMapping("{id}")
    public ResponseEntity<JobApp> updateJobAppPartial(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {  // partially updates a job
        JobApp updatedJobApp = jobAppService.updateJobAppPartial(id, updates);
        return ResponseEntity.ok(updatedJobApp);
    }
}
