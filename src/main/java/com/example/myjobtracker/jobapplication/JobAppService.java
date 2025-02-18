package com.example.myjobtracker.jobapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service  // same as @Component
public class JobAppService {

    private static final Logger logger = LoggerFactory.getLogger(JobAppService.class);
    private final JobAppRepository jobAppRepository;
    private final DataSource dataSource;

    public JobAppService(JobAppRepository jobAppRepository, DataSource dataSource) {
        this.jobAppRepository = jobAppRepository;
        this.dataSource = dataSource;

        // run this to check database connection when service starts
        // checkDatabaseConnection();
    }

    /* Log to see database connection details
    private void checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            logger.info("Connected to database: {}", connection.getCatalog());
        } catch (SQLException e) {
            logger.error("Database connection failed: {}", e.getMessage());
        }
    }

     */

    // fetch all job applications
    public List<JobApp> getAllJobApps() {
        List<JobApp> jobApps = jobAppRepository.findAll();
        logger.info("📌 Job applications fetched from database: {}", jobApps);
        return jobApps;
    }

    // insert new job application
    public void insertJobApp(JobApp jobApp) {
        jobAppRepository.save(jobApp);
        logger.info("🟢 Inserted new job application: {}", jobApp);
    }

    // fetch job application by ID
    public JobApp getJobAppById(Integer id) {
        return jobAppRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Application with id " + id + " not found"));
    }

    // delete a job application
    public void deleteJobApp(Integer id) {
        boolean exists = jobAppRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " not found");
        }
        jobAppRepository.deleteById(id);
        logger.info("🔴 Deleted job application with id: {}", id);
    }

    // fully update job application
    public JobApp updateJobApp(Integer id, JobApp jobAppDetails) {
        JobApp jobApp = getJobAppById(id);

        // Overwrite all fields
        jobApp.setCompanyName(jobAppDetails.getCompanyName());
        jobApp.setPosition(jobAppDetails.getPosition());
        jobApp.setAppliedDate(jobAppDetails.getAppliedDate());
        jobApp.setStatus(jobAppDetails.getStatus());
        jobApp.setNotes(jobAppDetails.getNotes());

        JobApp updatedJob = jobAppRepository.save(jobApp);
        logger.info("🟡 Updated job application: {}", updatedJob);
        return updatedJob;
    }

    // partially update job application
    public JobApp updateJobAppPartial(Integer id, Map<String, Object> updates) {
        JobApp jobApp = getJobAppById(id);

        // Apply updates dynamically
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

        JobApp updatedJob = jobAppRepository.save(jobApp);
        logger.info("🟡 Partially updated job application: {}", updatedJob);
        return updatedJob;
    }
}
