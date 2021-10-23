package vn.edu.hust.project3.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hust.project3.model.Product;
import vn.edu.hust.project3.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public List<Product> getAllProductByIds(List<Integer> ids) {
        return null;
    }
}
