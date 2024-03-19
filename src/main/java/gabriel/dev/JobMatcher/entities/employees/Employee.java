package gabriel.dev.JobMatcher.entities.employees;

import gabriel.dev.JobMatcher.dtos.EmployeeDto;
import gabriel.dev.JobMatcher.entities.companies.Company;
import gabriel.dev.JobMatcher.entities.jobs.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String cpf;
    private String description;
    private String areaAtuacao;
    private String password;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public Employee(EmployeeDto employeeDto) {
        this.firstName = employeeDto.firstName();
        this.lastName = employeeDto.lastName();
        this.cpf = employeeDto.cpf();
        this.description = employeeDto.description();
        this.areaAtuacao = employeeDto.areaAtuacao();
        this.password = employeeDto.password();
    }

}
