package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private int productId = 0;

    @Override
    public Product create(Product product) {
        product.setProductId(Integer.toString(++productId));
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
    public Product getProductId(int id) {
        Product product = null;
        Iterator<Product> productIterator = productRepository.findAll();

        while(productIterator.hasNext()) {
            Product item = productIterator.next();

            if (item.getProductId().equals(Integer.toString(id))) {
                product = item;
                break;
            }
        }
        return product;
    }

    @Override
    public Product editProduct(Product product) {
        productRepository.editProduct(product);
        return product;
    }
}
