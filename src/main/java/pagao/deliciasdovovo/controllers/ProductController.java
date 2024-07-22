package pagao.deliciasdovovo.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pagao.deliciasdovovo.dtos.ProductDTO;
import pagao.deliciasdovovo.entities.Product;
import pagao.deliciasdovovo.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable("id") @RequestParam long id) throws Exception {
        logger.info("[ProductController.getProductById]: Get product by id: {}", id);
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid ProductDTO productDTO) throws Exception {
        logger.info("[ProductController.addProduct]: Add product");
        return productService.addProduct(productDTO);
    }
}
