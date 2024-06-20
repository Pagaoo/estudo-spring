package pagao.deliciasdovovo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pagao.deliciasdovovo.dtos.CustomerDto;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.enums.UserType;
import pagao.deliciasdovovo.repositories.CustomerRepository;

import java.math.BigDecimal;
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
        return customerRepository.getCustomerById(id);
    }

    public Customer CreateCustomer(CustomerDto customerDto) {
            Customer newCustomer = new Customer(customerDto);
            return customerRepository.save(newCustomer);
    }

    public void validateTransaction(Customer sender, BigDecimal transactionValue) throws Exception {
        if (sender.getUserType() == UserType.LOJA) {
            logger.info("[Customer Service] Checking if user type can make a transaction");
            throw new Exception("LOJAS não podem fazer transações de compra");
        }

        if (sender.getBalance().compareTo(transactionValue) < 0 ) {
            throw new Exception("Valor insuficiente para realizar transação");
        }
    }

    public Customer saveCustomer(Customer customer) {
        return this.saveCustomer(customer);
    }
}
