package com.example.myjobtracker.jobapplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/job-applications")
public class JobAppController {

    private final String apiKey = System.getenv("API_KEY"); // api key for security. env!
    private final JobAppService jobAppService;

    public JobAppController(JobAppService jobAppService) {
        this.jobAppService = jobAppService;
    }

    // Get all job applications
    @GetMapping
    public ResponseEntity<?> getJobApps(@RequestHeader(value = "X-API-KEY", required = false) String requestKey) {
        if (!isAuthorized(requestKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.ok(jobAppService.getAllJobApps());
    }

    // Get a single job application by ID
    @GetMapping("{id}")
    public ResponseEntity<?> getJobAppById(@RequestHeader(value = "X-API-KEY", required = false) String requestKey,
                                           @PathVariable Integer id) {
        if (!isAuthorized(requestKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.ok(jobAppService.getJobAppById(id));
    }

    // Add a new job application
    @PostMapping
    public ResponseEntity<?> addNewJobApp(@RequestHeader(value = "X-API-KEY", required = false) String requestKey,
                                          @RequestBody JobApp jobApp) {
        if (!isAuthorized(requestKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        jobAppService.insertJobApp(jobApp);
        return ResponseEntity.ok(jobApp);
    }

    // Delete a job application
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteJobApp(@RequestHeader(value = "X-API-KEY", required = false) String requestKey,
                                          @PathVariable Integer id) {
        if (!isAuthorized(requestKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        jobAppService.deleteJobApp(id);
        return ResponseEntity.noContent().build();
    }

    // Update an existing job application
    @PutMapping("{id}")
    public ResponseEntity<?> updateJobApp(@RequestHeader(value = "X-API-KEY", required = false) String requestKey,
                                          @PathVariable Integer id,
                                          @RequestBody JobApp jobAppDetails) {
        if (!isAuthorized(requestKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        JobApp updatedJobApp = jobAppService.updateJobApp(id, jobAppDetails);
        return ResponseEntity.ok(updatedJobApp);
    }

    // Partially update a job application
    @PatchMapping("{id}")
    public ResponseEntity<?> updateJobAppPartial(@RequestHeader(value = "X-API-KEY", required = false) String requestKey,
                                                 @PathVariable Integer id,
                                                 @RequestBody Map<String, Object> updates) {
        if (!isAuthorized(requestKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        JobApp updatedJobApp = jobAppService.updateJobAppPartial(id, updates);
        return ResponseEntity.ok(updatedJobApp);
    }

    // Helper method to check API key
    private boolean isAuthorized(String requestKey) {
        return requestKey != null && requestKey.equals(apiKey);
    }
}
