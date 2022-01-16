package vn.edu.hust.project3.service;

import org.springframework.stereotype.Component;
import vn.edu.hust.project3.model.Bill;
import vn.edu.hust.project3.model.BillPhone;
import vn.edu.hust.project3.model.CartItem;

import java.util.List;
@Component
public interface BillPhoneService {

    BillPhone createBillPhone (Bill bill, CartItem item);
    BillPhone updateBillPhone(int id, BillPhone billPhone);
    void deleteBillPhoneByBillId(int id);
    BillPhone getBillPhoneById(int id);
    List<BillPhone> getAllBillPhone();
    List<BillPhone> getAllBillPhoneByBillId(int id);
    Double totalMoneyInBill(int billId);

}
