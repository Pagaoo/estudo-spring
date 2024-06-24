package pagao.deliciasdovovo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pagao.deliciasdovovo.dtos.ProductDTO;
import pagao.deliciasdovovo.entities.Product;
import pagao.deliciasdovovo.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        logger.info("[Product Service] Getting all products");
        return productRepository.findAll();
    }

    public Product getProductById(long id) throws Exception {
        logger.info("[Product Service] Get product by id: {}", id);
        try {
            return productRepository.findProductById(id);
        } catch (Exception e) {
            throw new Exception("Produto não encontrado");
        }
    }

    public Product addProduct(ProductDTO productDTO) throws Exception {
        logger.info("[Product Service] Adding new product");
        try {
            Product newProduct = new Product(productDTO);
            return productRepository.save(newProduct);
        } catch (Exception e) {
            logger.error("[Product Service] Error adding new product");
            throw new Exception("Não foi possivel adicionar um novo produto");
        }
    }
}
