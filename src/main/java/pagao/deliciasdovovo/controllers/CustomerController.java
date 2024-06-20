package pagao.deliciasdovovo.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pagao.deliciasdovovo.dtos.CustomerDto;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.services.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/customers")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        logger.info("[CustomerController] creating customer]");
        return customerService.CreateCustomer(customerDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        logger.info("[CustomerController] getting all customers");
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable("id") @RequestParam Long id) throws Exception {
        logger.info("[CustomerController] getting customer by id");
        return customerService.getCustomerById(id);
    }
}
