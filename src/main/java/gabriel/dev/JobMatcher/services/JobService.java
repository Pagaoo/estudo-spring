package gabriel.dev.JobMatcher.services;

import gabriel.dev.JobMatcher.dtos.JobDto;
import gabriel.dev.JobMatcher.entities.jobs.Job;
import gabriel.dev.JobMatcher.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(JobDto jobDto) {
        Job newJob = new Job(jobDto);
        return jobRepository.save(newJob);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
