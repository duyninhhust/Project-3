package vn.edu.hust.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project3.model.BillPhone;

import java.util.List;

@Repository
public interface BillPhoneRepository extends JpaRepository<BillPhone, Integer> {
    List<BillPhone> findAllByBillId(int billId);

    @Modifying
    void deleteByBill_Id(int id);

    @Query("SELECT SUM(bp.price * bp.quantity) FROM BillPhone bp WHERE bp.bill.id = ?1")
    Double getTotalMoneyInBill(int billId);
}
