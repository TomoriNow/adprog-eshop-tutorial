package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

import  static  org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @Mock
    private Model model;
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
    void testControllerCreateProductPage() {
        String productName = productController.createProductPage(model);

        assertEquals("CreateProduct", productName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testControllerCreateProductPost() {
        Product product = new Product();
        String productName = productController.createProductPost(product, model);

        assertEquals("redirect:list", productName);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testControllerProductListPage() {
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

        when(productService.findAll()).thenReturn(productList);

        String productName = productController.productListPage(model);

        assertEquals("ProductList", productName);
        verify(model, times(1)).addAttribute(eq("products"), eq(productList));
    }

    @Test
    void testControllerDeleteProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        String productName = productController.deleteProduct(product1.getProductId());
        assertEquals("redirect:/product/list", productName);

        verify(productService, times(1)).deleteProductById(product1.getProductId());
    }

    @Test
    void testControllerEdit() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        String productName = productController.edit(product1);

        assertEquals("redirect:list", productName);
        verify(productService, times(1)).editProduct(product1);
    }

    @Test
    void testControllerEditPage() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        when(productService.getProductId(product1.getProductId())).thenReturn(product1);

        String productName = productController.editPage(model, product1.getProductId());

        assertEquals("EditProduct", productName);
        verify(model, times(1)).addAttribute(eq("product"), eq(product1));
    }
}
