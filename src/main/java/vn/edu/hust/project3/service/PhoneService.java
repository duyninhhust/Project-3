package vn.edu.hust.project3.service;

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
}
