package vn.edu.hust.project3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hust.project3.model.Bill;
import vn.edu.hust.project3.model.BillPhone;
import vn.edu.hust.project3.model.CartItem;
import vn.edu.hust.project3.repository.BillPhoneRepository;
import vn.edu.hust.project3.service.BillPhoneService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BillPhoneServiceImpl implements BillPhoneService {

    private final BillPhoneRepository billPhoneRepository;
    @Override
    public BillPhone createBillPhone(Bill bill, CartItem item) {
        BillPhone billPhone = new BillPhone();
        billPhone.setBill(bill);
        billPhone.setPrice(item.getPhone().getPrice());
        billPhone.setPhone(item.getPhone());
        billPhone.setQuantity(item.getQuantity());
        billPhoneRepository.save(billPhone);
        return billPhone;
    }

    @Override
    public BillPhone updateBillPhone(int id, BillPhone billPhone) {
        return null;
    }

    @Override
    public void deleteBillPhoneByBillId(int id) {
        billPhoneRepository.deleteByBill_Id(id);
    }

    @Override
    public BillPhone getBillPhoneById(int id) {
        return null;
    }

    @Override
    public List<BillPhone> getAllBillPhone() {
        return null;
    }

    @Override
    public List<BillPhone> getAllBillPhoneByBillId(int id) {
        return billPhoneRepository.findAllByBillId(id);
    }

    @Override
    public Double totalMoneyInBill(int billId) {
        return billPhoneRepository.getTotalMoneyInBill(billId);
    }
}
