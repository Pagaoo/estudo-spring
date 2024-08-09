package pagao.deliciasdovovo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pagao.deliciasdovovo.dtos.ProductDTO;
import pagao.deliciasdovovo.entities.Product;
import pagao.deliciasdovovo.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private ProductDTO productDTO;
    private Product product;

    @BeforeEach
    public void setUp() {
        productDTO = new ProductDTO(
                "produto 1",
                "descrição 1",
                "Tipo do doce",
                new BigDecimal("10.00"),
                100,
                "image.jpeg"
        );

        product = new Product(productDTO);
        product.setId(1L);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertFalse(products.isEmpty());
        verify(productRepository, times(1)).findAll();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void getProductById() throws Exception {
        when(productRepository.findProductById(product.getId())).thenReturn(product);

        Product foundProduct = productService.getProductById(product.getId());

        assertNotNull(foundProduct);
        assertEquals(product.getId(), foundProduct.getId());
        verify(productRepository, times(1)).findProductById(product.getId());
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void addProduct() throws Exception {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product addedProduct = productService.addProduct(productDTO);

        assertNotNull(addedProduct);
        verify(productRepository, times(1)).save(any(Product.class));
        verifyNoMoreInteractions(productRepository);
    }
}
