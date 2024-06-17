package pagao.deliciasdovovo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pagao.deliciasdovovo.dtos.CustomerDto;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers() {
        logger.info("[Customer Service] getting all customers");
        return customerRepository.findAll();
    }


    public Customer getCustomerById(Long id) {
        logger.info("[Customer Service] getting customer by id: {}", id);
        return customerRepository.findById(id).orElse(null);
    }

    public Customer CreateCustomer(CustomerDto customerDto) {
        Customer newCustomer = new Customer(customerDto);
        return customerRepository.save(newCustomer);
    }
}
