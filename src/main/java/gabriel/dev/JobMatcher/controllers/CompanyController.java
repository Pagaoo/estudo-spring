package gabriel.dev.JobMatcher.controllers;

import gabriel.dev.JobMatcher.dtos.CompanyDto;
import gabriel.dev.JobMatcher.entities.companies.Company;
import gabriel.dev.JobMatcher.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    public Company createEmployee(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}
