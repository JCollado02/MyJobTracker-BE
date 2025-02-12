package com.example.myjobtracker.jobapplication;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service  // same as @component
public class JobAppService {
    public List<JobApp> getJobApps() {  // Server returns JSON array
        return List.of(  // Server returns a list of job apps
                new JobApp(  // Creates a job app object
                        1L,
                        "Sample Company",
                        "Junior Java Developer",
                        LocalDate.of(2025, Month.JANUARY, 5),
                        "Pending Review",
                        "This is a sample application!!"

                )
        );
    }
}
