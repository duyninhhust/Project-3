package vn.edu.hust.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project3.model.Phone;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    List<Phone> findByIdIn(List<Integer> ids);

    @Query(value = "select * from Phone p order by p.created_date desc limit 3", nativeQuery = true)
    List<Phone> findNewestPhone();

    @Query(value = "select * from Phone p order by p.quantity desc limit 3", nativeQuery = true)
    List<Phone> findTopPhoneHasTheMostQuantity();

    @Query(value = "select * from Phone p order by p.price desc limit 4", nativeQuery = true)
    List<Phone> findTopPhoneHasTheMostPrice();

    @Query(value = "SELECT * FROM Phone p WHERE p.name LIKE %?1% " , nativeQuery = true)
    List<Phone> findPhoneByName(String name);

    @Query(value = "SELECT * FROM Product p WHERE p.price >= ?1 AND p.price <= ?2 ", nativeQuery = true)
    List<Phone> findPhoneByPrice(double p1, double p2);
}
