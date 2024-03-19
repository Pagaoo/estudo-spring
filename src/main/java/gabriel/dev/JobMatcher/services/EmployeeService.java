package gabriel.dev.JobMatcher.services;

import gabriel.dev.JobMatcher.dtos.EmployeeDto;
import gabriel.dev.JobMatcher.entities.employees.Employee;
import gabriel.dev.JobMatcher.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = new Employee(employeeDto);
        return employeeRepository.save(newEmployee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
