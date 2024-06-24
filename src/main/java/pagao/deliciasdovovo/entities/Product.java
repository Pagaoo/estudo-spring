package pagao.deliciasdovovo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pagao.deliciasdovovo.dtos.ProductDTO;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String productType;
    private BigDecimal price;
    private int quantity;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.description = productDTO.description();
        this.productType = productDTO.productType();
        this.price = productDTO.price();
        this.quantity = productDTO.quantity();
    }
}
