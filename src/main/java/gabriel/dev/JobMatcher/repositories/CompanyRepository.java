package gabriel.dev.JobMatcher.repositories;

import gabriel.dev.JobMatcher.entities.companies.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
