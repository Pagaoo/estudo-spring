package pagao.deliciasdovovo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pagao.deliciasdovovo.dtos.CustomerDTO;
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


    public Customer getCustomerById(Long id) throws Exception {
        logger.info("[Customer Service] getting customer by id: {}", id);
        try {
            return customerRepository.getCustomerById(id);
        } catch (Exception e) {
            throw new Exception("Cliente não encontrado");
        }

    }

    public Customer CreateCustomer(CustomerDTO customerDto) {
        logger.info("[Customer Service] creating customer: {}", customerDto);
        Customer newCustomer = new Customer(customerDto);
        return customerRepository.save(newCustomer);
    }

    public void validateTransaction(Customer sender, Customer receiver, BigDecimal transactionValue) throws Exception {
        logger.info("[Customer Service] Checking if a customer can make a transaction");
        if (sender.getUserType() == UserType.LOJA) {
            throw new Exception("LOJAS não podem fazer transações de compra");
        }

        if (sender.getUserType() == UserType.COMUM && receiver.getUserType() == UserType.COMUM) {
            throw new Exception("Usuários comuns não podem fazer transações entre si");
        }

        if (sender.getBalance().compareTo(transactionValue) < 0 ) {
            throw new Exception("Valor insuficiente para realizar a transação");
        }
    }

    public Customer saveCustomer(Customer customer) throws Exception {
        try {
            logger.info("[Customer Service] saving customer");
            return customerRepository.save(customer);
        } catch (Exception e) {
            logger.error("Erro ao salvar cliente");
            throw new Exception("Erro ao salvar o cliente");
        }
    }
}
