package gabriel.dev.JobMatcher.services;

import gabriel.dev.JobMatcher.dtos.CompanyDto;
import gabriel.dev.JobMatcher.entities.companies.Company;
import gabriel.dev.JobMatcher.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(CompanyDto companyDto) {
        Company newCompany = new Company(companyDto);
        return companyRepository.save(newCompany);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
