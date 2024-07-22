package pagao.deliciasdovovo.dtos;

import java.math.BigDecimal;

public record ProductDTO(String name, String description, String productType, BigDecimal price, int quantity,
                         String productImage) {
}
