package gabriel.dev.JobMatcher.entities.jobs;

import gabriel.dev.JobMatcher.dtos.JobDto;
import gabriel.dev.JobMatcher.entities.companies.Company;
import gabriel.dev.JobMatcher.entities.employees.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job")
    private List<Employee> employees;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public Job(JobDto jobDto) {
        this.name = jobDto.name();
        this.description = jobDto.description();
    }
}
