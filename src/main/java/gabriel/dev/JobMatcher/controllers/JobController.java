package gabriel.dev.JobMatcher.controllers;

import gabriel.dev.JobMatcher.dtos.JobDto;
import gabriel.dev.JobMatcher.entities.jobs.Job;
import gabriel.dev.JobMatcher.services.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody @Valid JobDto jobDto) {
        return jobService.createJob(jobDto);
    }

    @GetMapping()
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

}
