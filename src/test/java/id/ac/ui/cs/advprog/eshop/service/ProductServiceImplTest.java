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
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Product savedProduct = productService.create(product);

        assertNotNull(savedProduct.getId());
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testServiceFindAll() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
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
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        Product savedProduct = productService.getId(product.getId());

        assertNotNull(savedProduct);
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testServiceGetProductIdWhenDifferent() {
        String initialId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        Product product = new Product();
        product.setId(initialId);
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        String updatedId = "a0f9de46-90b1-437d-a0bf-d0821dde9096";
        product.setId(updatedId);

        Product savedProduct = productService.getId(initialId);

        assertNull(savedProduct);
        assertNotEquals(product.getId(), initialId);
        assertEquals(product.getId(), updatedId);
    }

    @Test
    void testServiceDeleteProductById() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        when(productRepository.deleteProduct(any())).thenReturn(true);

        boolean result = productService.deleteProductById(product.getId());

        assertTrue(result);
        assertNotNull(product.getId());

        verify(productRepository, times(1)).findAll();
        verify(productRepository, times(1)).deleteProduct(product);
    }

    @Test
    void testServiceDeleteProductByIdWhenProductDoesNotExist() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());

        boolean result = productService.deleteProductById(product.getId());

        assertFalse(result);

        verify(productRepository, times(1)).findAll();
        verify(productRepository, never()).deleteProduct(any());
    }

    @Test
    void testServiceDeleteProductByIdWhenProductHasNoId() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        String idCheck = product.getId();

        assertNull(idCheck);

        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());


        boolean result = productService.deleteProductById(product.getId());

        assertFalse(result);

        verify(productRepository, times(1)).findAll();
        verify(productRepository, never()).deleteProduct(any());
    }

    @Test
    void testServiceEditProduct() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        when(productRepository.editProduct(product2)).thenReturn(product2);

        Product editedProduct = productService.editProduct(product2);

        assertEquals(product2.getName(), editedProduct.getName());
        assertEquals(product2.getQuantity(), editedProduct.getQuantity());
        assertNotEquals(product1.getName(), editedProduct.getName());
        assertNotEquals(product1.getQuantity(), editedProduct.getQuantity());
    }

}
