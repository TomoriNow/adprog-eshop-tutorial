package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean deleteProduct(Product product) {
        return productData.remove(product);
    }

    public Product editProduct(Product product) {
        for (int i=0; i < productData.size(); i++) {
            Product item = productData.get(i);
            if (item.getId().equals(product.getId())) {
                return productData.set(i, product);
            }
        }
        return null;
    }
}
