package vn.edu.hust.project3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.hust.project3.exception.ProductNotFoundException;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.repository.PhoneRepository;
import vn.edu.hust.project3.service.PhoneService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Override
    public Phone createPhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone updatePhone(int id, Phone phone) {
        Phone phone1 = findProductById(id);
        phone1.setOldPrice(phone.getOldPrice());
        phone1.setName(phone.getName());
        phone1.setPrice(phone.getPrice());
        phone1.setImage(phone.getImage());
        phone1.setIntroduction(phone.getIntroduction());
        phone1.setQuantity(phone.getQuantity());
        phone1.setCategory(phone.getCategory());
        phone1.setChip(phone.getChip());
        phone1.setFrontCamera(phone.getFrontCamera());
        phone1.setOperatingSystem(phone.getOperatingSystem());
        phone1.setPinCharger(phone.getPinCharger());
        phone1.setRearCamera(phone.getRearCamera());
        phone1.setRam(phone.getRam());
        phone1.setRom(phone.getRom());
        phone1.setScreen(phone.getScreen());
        phone1.setSim(phone.getSim());

        return phoneRepository.save(phone1);
    }

    @Override
    public void deletePhone(int id) {
        Phone phone = findProductById(id);
        phoneRepository.delete(phone);
    }

    @Override
    public Phone getPhoneById(int id) {
        return findProductById(id);
    }

    @Override
    public List<Phone> getAllPhone() {
        return phoneRepository.findAll();
    }

    @Override
    public List<Phone> getAllPhoneByIds(List<Integer> ids) {
        return phoneRepository.findByIdIn(ids);
    }

    @Override
    public Page<Phone> findPhonePaginated(int pageNumber, int pageSize, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return phoneRepository.findAll(pageable);
    }

    private Phone findProductById(int id) {
        Optional<Phone> optional = phoneRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ProductNotFoundException(String.format("Could not found product with ID= [%s]", id));
        }
        return optional.get();
    }
}
