package pagao.deliciasdovovo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pagao.deliciasdovovo.dtos.CustomerDTO;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.enums.UserType;
import pagao.deliciasdovovo.services.CustomerService;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO(
                "Roger",
                "Roberto",
                "roger@example.com",
                "999999999",
                UserType.COMUM,
                new BigDecimal("100.58")
        );


        customer = new Customer(customerDTO);
        customer.setId(1L);
    }


    @Test
    void createCustomerTest() throws Exception {
        when(customerService.CreateCustomer(customerDTO)).thenReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Roger"))
                .andExpect(jsonPath("$.lastName").value("Roberto"))
                .andExpect(jsonPath("$.email").value("roger@example.com"))
                .andExpect(jsonPath("$.phone").value("999999999"))
                .andExpect(jsonPath("$.userType").value(UserType.COMUM.toString()))
                .andExpect(jsonPath("$.balance").value("100.58"));

        verify(customerService).CreateCustomer(customerDTO);
    }

    @Test
    void getAllCustomersTest() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Roger"))
                .andExpect(jsonPath("$[0].lastName").value("Roberto"))
                .andExpect(jsonPath("$[0].email").value("roger@example.com"))
                .andExpect(jsonPath("$[0].phone").value("999999999"))
                .andExpect(jsonPath("$[0].userType").value(UserType.COMUM.toString()))
                .andExpect(jsonPath("$[0].balance").value("100.58"));

        verify(customerService).getAllCustomers();
    }
}
