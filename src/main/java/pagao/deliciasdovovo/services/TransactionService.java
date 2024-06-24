package pagao.deliciasdovovo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pagao.deliciasdovovo.dtos.TransactionDTO;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.entities.Transaction;
import pagao.deliciasdovovo.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;

    public TransactionService(TransactionRepository transactionRepository, CustomerService customerService) {
        this.transactionRepository = transactionRepository;
        this.customerService = customerService;
    }

    public List<Transaction> getAllTransactions() {
        logger.info("[Transaction Service] Get all transactions");
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(long id) throws Exception {
        logger.info("[Transaction Service] Get transaction by id: {}", id);
        try {
            return transactionRepository.findTransactionById(id);
        } catch (Exception e) {
            throw new Exception("Transação não encontrada");
        }
    }

    public Transaction saveTransaction(TransactionDTO transactionDto) throws Exception {
        logger.info("[Transaction Service] Saving transaction: {}", transactionDto);
        Customer sender = this.customerService.getCustomerById(transactionDto.sender_id());
        Customer receiver = this.customerService.getCustomerById(transactionDto.receiver_id());

        customerService.validateTransaction(sender, receiver, transactionDto.value());


        Transaction newTransaction = new Transaction();
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setValue(transactionDto.value());
        newTransaction.setTransactionDate(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

        this.transactionRepository.save(newTransaction);
        this.customerService.saveCustomer(sender);
        this.customerService.saveCustomer(receiver);

        return newTransaction;
    }
}
