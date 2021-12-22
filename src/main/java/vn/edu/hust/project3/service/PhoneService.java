package vn.edu.hust.project3.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.edu.hust.project3.model.Phone;

import java.util.List;

@Component
public interface PhoneService {

    Phone createPhone (Phone phone);
    Phone updatePhone(int id, Phone phone);
    void deletePhone(int id);
    Phone getPhoneById(int id);
    List<Phone> getAllPhone();
    List<Phone> getAllPhoneByIds(List<Integer> ids);
    Page<Phone> findPhonePaginated(int pageNumber, int pageSize, String sortField, String sortDir);
    List<Phone> getFeaturedPhone();
    List<Phone> getMostQuantityPhone();
    List<Phone> getNewestPhone();
    List<Phone> searchPhone(String name);
    List<Phone> getPhoneByPrice(double p1, double p2);
    List<Phone> getPhoneByCategory(int id);
}
