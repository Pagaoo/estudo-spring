package gabriel.dev.JobMatcher.entities.companies;

import gabriel.dev.JobMatcher.dtos.CompanyDto;
import gabriel.dev.JobMatcher.entities.employees.Employee;
import gabriel.dev.JobMatcher.entities.jobs.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeFantasia;
    @Column(unique = true)
    private String cnpj;
    private String description;
    private String razaoSocial;
    private String password;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Job> jobs;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Employee> employeesList;

    public Company(CompanyDto companyDto) {
        this.nomeFantasia = companyDto.nomeFantasia();
        this.cnpj = companyDto.cnpj();
        this.description = companyDto.description();
        this.razaoSocial = companyDto.razaoSocial();
        this.password = companyDto.password();
    }

}
