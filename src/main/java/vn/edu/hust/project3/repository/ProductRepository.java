package vn.edu.hust.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project3.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
