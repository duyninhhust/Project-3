package vn.edu.hust.project3.service;

import vn.edu.hust.project3.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct (Product product);
    Product updateProduct(int id, Product product);
    void deleteProduct(int id);
    Product getProductById(int id);
    List<Product> getAllProduct();
    List<Product> getAllProductByIds(List<Integer> ids);
}
