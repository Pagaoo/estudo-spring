package pagao.deliciasdovovo.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pagao.deliciasdovovo.dtos.TransactionDTO;
import pagao.deliciasdovovo.entities.Customer;
import pagao.deliciasdovovo.entities.Transaction;
import pagao.deliciasdovovo.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;

    public TransactionService(TransactionRepository transactionRepository, CustomerService customerService) {
        this.transactionRepository = transactionRepository;
        this.customerService = customerService;
    }

    public List<TransactionDTO> getAllTransactions() {
        logger.info("[Transaction Service] Get all transactions");
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            logger.error("[Transaction Service] No transactions found");
            throw new RuntimeException("No transactions found");
        }
        //.stream() -> Convertendo a lista de transactions e uma sequencia de elementos STREAM
        //Usando o map para se referir a função que converte as transactions para as transactionsDTO
        //.collect -> Transformando os elementos STREAM de volta em coleção com o COLLECT
        //Collectors.toList() -> E colocando os elementos em uma lista
        return transactions.stream().map(this::convertTransactionToTransactionDTO).collect(Collectors.toList());
    }

    public TransactionDTO findTransactionById(long id) throws Exception {
        logger.info("[Transaction Service] Get transaction by id: {}", id);
        Transaction transaction = transactionRepository.findTransactionById(id);
        if (transaction == null) {
            logger.error("[Transaction Service] Transaction with id: {} not found", id);
            throw new Exception("Transaction not found");
        }
        return convertTransactionToTransactionDTO(transaction);
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

    private TransactionDTO convertTransactionToTransactionDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getSender().getId(),
                transaction.getReceiver().getId(),
                transaction.getValue(),
                transaction.getTransactionDate()
        );
    }

}
