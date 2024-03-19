package gabriel.dev.JobMatcher.repositories;

import gabriel.dev.JobMatcher.entities.employees.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
