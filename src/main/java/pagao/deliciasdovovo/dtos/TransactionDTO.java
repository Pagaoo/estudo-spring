package pagao.deliciasdovovo.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(Long sender_id, Long receiver_id, BigDecimal value, LocalDateTime transactionDate) {
}
