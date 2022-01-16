package vn.edu.hust.project3.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.edu.hust.project3.model.Bill;

import java.util.List;

@Component
public interface BillService {

    Bill createBill (Bill bill);
    Bill updateBill(int id, Bill bill);
    void deleteBill(int id);
    Bill getBillById(int id);
    List<Bill> getAllBill();
    Page<Bill> findPaginated(int pageNumber, int pageSize, String sortField, String sortDir);
    void cancelBill(int id);
    void confirmBill(int id);
}
