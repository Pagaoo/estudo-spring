package pagao.deliciasdovovo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pagao.deliciasdovovo.dtos.CustomerDTO;
import pagao.deliciasdovovo.dtos.TransactionDTO;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.entities.Transaction;
import pagao.deliciasdovovo.enums.UserType;
import pagao.deliciasdovovo.repositories.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerService customerService;

    private Transaction transaction;
    private TransactionDTO transactionDTO;
    private CustomerDTO senderDTO;
    private CustomerDTO receiverDTO;
    private Customer sender;
    private Customer receiver;

    @BeforeEach
    public void setUp() {
        senderDTO = new CustomerDTO(
                "Roger",
                "Roberto",
                "roger@example.com",
                "999999999",
                UserType.COMUM,
                new BigDecimal("100.00")
        );

        sender = new Customer(senderDTO);
        sender.setId(1L);

        receiverDTO = new CustomerDTO(
                "Roger",
                "Roberto",
                "roger@example.com",
                "999999999",
                UserType.COMUM,
                new BigDecimal("100.00")
        );

        receiver = new Customer(receiverDTO);
        receiver.setId(2L);

        transactionDTO = new TransactionDTO(
                sender.getId(),
                receiver.getId(),
                new BigDecimal("100.00"),
                LocalDateTime.now()
        );

        transaction = new Transaction(sender, receiver, transactionDTO.value(), transactionDTO.transactionDate());
        transaction.setId(1L);
    }

    @Test
    void getAllTransactions() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));

        List<TransactionDTO> transactions = transactionService.getAllTransactions();

        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());
        verify(transactionRepository, times(1)).findAll();
        verifyNoMoreInteractions(transactionRepository);
    }

    @Test
    void getTransactionById() throws Exception {
        when(transactionRepository.findTransactionById(transaction.getId())).thenReturn(transaction);

        TransactionDTO foundTransaction = transactionService.findTransactionById(transaction.getId());
        assertNotNull(foundTransaction);
        assertEquals(transactionDTO.value(), foundTransaction.value());
        assertEquals(transactionDTO.transactionDate(), foundTransaction.transactionDate());
        assertEquals(transactionDTO.sender_id(), foundTransaction.sender_id());
        assertEquals(transactionDTO.receiver_id(), foundTransaction.receiver_id());
        assertEquals(transactionDTO.receiver_id(), foundTransaction.receiver_id());
        verify(transactionRepository, times(1)).findTransactionById(transaction.getId());
        verifyNoMoreInteractions(transactionRepository);
    }

    @Test
    void createTransaction() throws Exception {
        when(customerService.getCustomerById(sender.getId())).thenReturn(sender);
        when(customerService.getCustomerById(receiver.getId())).thenReturn(receiver);

        doNothing().when(customerService).validateTransaction(sender, receiver, transactionDTO.value());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        when(customerService.saveCustomer(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction createdTransaction = transactionService.saveTransaction(transactionDTO);

        assertNotNull(createdTransaction);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(customerService, times(2)).getCustomerById(anyLong());
        verify(customerService, times(1)).validateTransaction(sender, receiver, transactionDTO.value());
        verify(customerService, times(2)).saveCustomer(any(Customer.class));
        verifyNoMoreInteractions(transactionRepository, customerService);
    }
}
