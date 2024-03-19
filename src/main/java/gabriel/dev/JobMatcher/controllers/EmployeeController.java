package gabriel.dev.JobMatcher.controllers;

import gabriel.dev.JobMatcher.dtos.EmployeeDto;
import gabriel.dev.JobMatcher.entities.employees.Employee;
import gabriel.dev.JobMatcher.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
