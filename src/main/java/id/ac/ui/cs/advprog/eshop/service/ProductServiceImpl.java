package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoryInterface productRepository;

    @Override
    public Product create(Product product) {
        product.setId(String.valueOf(UUID.randomUUID()));
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product getId(String id) {
        Product product = null;
        Iterator<Product> productIterator = productRepository.findAll();

        while(productIterator.hasNext()) {
            Product item = productIterator.next();

            if (item.getId().equals(id)) {
                product = item;
                break;
            }
        }
        return product;
    }

    @Override
    public boolean deleteProductById(String productId) {
        Product id = getId(productId);

        return id != null && productRepository.deleteProduct(id);
    }

    @Override
    public Product editProduct(Product product) {
        productRepository.editProduct(product);
        return product;
    }
}
