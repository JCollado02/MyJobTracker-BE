package com.example.myjobtracker.jobapplication;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service  // same as @component
public class JobAppService {

    private final JobAppRepository jobAppRepository;

    public JobAppService(JobAppRepository jobAppRepository) {
        this.jobAppRepository = jobAppRepository;
    }

    public List<JobApp> getAllJobApps() {  // Server returns JSON array
        return jobAppRepository.findAll();
    }

    public void insertJobApp(JobApp jobApp) {  // TODO: Implement validation, i.e. not-null values
        jobAppRepository.save(jobApp);
    }

    public JobApp getJobAppById(Integer id) {
        return jobAppRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Job Application with id " + id + " not found"));
    }

    public void deleteJobApp(Integer id) {
        boolean exists = jobAppRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    id + " not found"
            );
        }
        jobAppRepository.deleteById(id);
    }

    public JobApp updateJobApp(Integer id, JobApp jobAppDetails) {  // requires all fields

        JobApp jobApp = getJobAppById(id); // Fetch existing job application

        // Overwrite all fields
        jobApp.setCompanyName(jobAppDetails.getCompanyName());
        jobApp.setPosition(jobAppDetails.getPosition());
        jobApp.setAppliedDate(jobAppDetails.getAppliedDate());
        jobApp.setStatus(jobAppDetails.getStatus());
        jobApp.setNotes(jobAppDetails.getNotes());

        return jobAppRepository.save(jobApp); // Save and return updated job application
    }

    public JobApp updateJobAppPartial(Integer id, Map<String, Object> updates) {  // Only updates provided fields

        JobApp jobApp = getJobAppById(id); // Fetch existing job application

        // Loop through provided updates and apply them dynamically
        updates.forEach((key, value) -> {
            switch (key) {
                case "companyName" -> jobApp.setCompanyName((String) value);
                case "position" -> jobApp.setPosition((String) value);
                case "appliedDate" -> jobApp.setAppliedDate(LocalDate.parse((String) value));
                case "status" -> jobApp.setStatus((String) value);
                case "notes" -> jobApp.setNotes((String) value);
                default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid field: " + key);
            }
        });

        return jobAppRepository.save(jobApp); // Save and return updated job application
    }
}
