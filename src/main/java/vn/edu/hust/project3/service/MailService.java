package vn.edu.hust.project3.service;

import vn.edu.hust.project3.dto.ContactMail;
import vn.edu.hust.project3.model.BillPhone;
import vn.edu.hust.project3.model.User;

import java.util.List;

public interface MailService {

    void sentContactMail(ContactMail mail);

    void sendOrderMail(User user);
}
