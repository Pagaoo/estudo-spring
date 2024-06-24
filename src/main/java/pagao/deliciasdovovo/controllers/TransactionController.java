package pagao.deliciasdovovo.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pagao.deliciasdovovo.dtos.TransactionDTO;
import pagao.deliciasdovovo.entities.Transaction;
import pagao.deliciasdovovo.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody @Valid TransactionDTO transactionDto) throws Exception {
        logger.info("[TransactionController] creating a transaction between two customer");
        return this.transactionService.saveTransaction(transactionDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAllTransactions() {
        logger.info("[TransactionController] getting all transactions");
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO getTransaction(@PathVariable("id") @RequestParam Long id) throws Exception {
        logger.info("[TransactionController] getting transaction by id {}", id);
        return transactionService.findTransactionById(id);
    }
}
