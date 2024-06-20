package pagao.deliciasdovovo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pagao.deliciasdovovo.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
