package gabriel.dev.JobMatcher.repositories;

import gabriel.dev.JobMatcher.entities.jobs.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
