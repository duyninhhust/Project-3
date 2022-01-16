package vn.edu.hust.project3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.hust.project3.exception.ProductNotFoundException;
import vn.edu.hust.project3.model.Bill;
import vn.edu.hust.project3.repository.BillRepository;
import vn.edu.hust.project3.service.BillService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Override
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(int id, Bill bill) {
        return null;
    }

    @Override
    @Transactional
    public void deleteBill(int id) {
        Bill bill = findBillById(id);
        billRepository.delete(bill);
    }

    @Override
    public Bill getBillById(int id) {
        return findBillById(id);
    }

    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public Page<Bill> findPaginated(int pageNumber, int pageSize, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return billRepository.findAll(pageable);
    }

    @Override
    public void cancelBill(int id) {
        billRepository.cancelBill(id);
    }

    @Override
    public void confirmBill(int id) {
        billRepository.confirmBill(id);
    }

    private Bill findBillById(int id) {
        Optional<Bill> optional = billRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ProductNotFoundException(String.format("Could not found bill with ID= [%s]", id));
        }
        return optional.get();
    }
}
