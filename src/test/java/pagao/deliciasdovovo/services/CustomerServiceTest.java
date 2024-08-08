package pagao.deliciasdovovo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pagao.deliciasdovovo.dtos.CustomerDTO;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.enums.UserType;
import pagao.deliciasdovovo.repositories.CustomerRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private CustomerDTO customerDTO;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customerDTO = new CustomerDTO(
                "Roger",
                "Roberto",
                "roger@example.com",
                "999999999",
                UserType.COMUM,
                new BigDecimal("100.00")
        );

        customer = new Customer(customerDTO);
        customer.setId(1L);
    }

    @Test
    void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        List<Customer> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void getCustomerById() throws Exception {
        when(customerRepository.getCustomerById(1L)).thenReturn(customer);

        Customer foundCustomer = customerService.getCustomerById(1L);
        assertNotNull(foundCustomer);
        assertFalse(foundCustomer.getId() != 1L);
        assertEquals(customer.getId(), foundCustomer.getId());
        verify(customerRepository, times(1)).getCustomerById(1L);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void createCustomer() throws Exception {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer createdCustomer = customerService.CreateCustomer(customerDTO);

        assertNotNull(createdCustomer);
        verify(customerRepository, times(1)).save(any(Customer.class));
        verifyNoMoreInteractions(customerRepository);
    }
}
