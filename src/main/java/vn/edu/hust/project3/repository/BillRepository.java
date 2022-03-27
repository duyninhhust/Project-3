package vn.edu.hust.project3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project3.model.Bill;
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Modifying
    @Query(value = "UPDATE Bill b SET b.status = 3 WHERE b.id = ?1", nativeQuery = true)
    void cancelBill(int id);

    @Modifying
    @Query(value = "UPDATE Bill b SET b.status = 2 WHERE b.id = ?1", nativeQuery = true)
    void confirmBill(int id);

    Page<Bill> findByUserId(int userId, Pageable pageable);
}
