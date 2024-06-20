package pagao.deliciasdovovo.dtos;

import java.math.BigDecimal;

public record TransactionDto(Long sender_id, Long receiver_id, BigDecimal value) {
}
