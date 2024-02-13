package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testServiceCreateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productService.create(product);

        assertNotNull(savedProduct.getProductId());
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testServiceFindAll() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);


        List<Product> productList = List.of(product1, product2);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> retrievedProducts = productService.findAll();

        assertEquals(productList.size(), retrievedProducts.size());
    }

    @Test
    void testServiceGetProductId() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        Product savedProduct = productService.getProductId(product.getProductId());

        assertNotNull(savedProduct);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testServiceGetProductIdWhenDifferent() {
        String initialId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        Product product = new Product();
        product.setProductId(initialId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        String updatedId = "a0f9de46-90b1-437d-a0bf-d0821dde9096";
        product.setProductId(updatedId);

        Product savedProduct = productService.getProductId(initialId);

        assertNull(savedProduct);
        assertNotEquals(product.getProductId(), initialId);
        assertEquals(product.getProductId(), updatedId);
    }

    @Test
    void testServiceDeleteProductById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        when(productRepository.deleteProduct(any())).thenReturn(true);

        boolean result = productService.deleteProductById(product.getProductId());

        assertTrue(result);
    }

    @Test
    void testServiceDeleteProductByIdWhenProductDoesNotExist() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());

        boolean result = productService.deleteProductById(product.getProductId());

        assertFalse(result);

        verify(productRepository, times(1)).findAll();
        verify(productRepository, never()).deleteProduct(any());
    }

    @Test
    void testServiceEditProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        when(productRepository.editProduct(product2)).thenReturn(product2);

        Product editedProduct = productService.editProduct(product2);

        assertEquals(product2.getProductName(), editedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), editedProduct.getProductQuantity());
        assertNotEquals(product1.getProductName(), editedProduct.getProductName());
        assertNotEquals(product1.getProductQuantity(), editedProduct.getProductQuantity());
    }

}
