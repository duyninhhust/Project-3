package vn.edu.hust.project3.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.edu.hust.project3.model.User;

import java.util.List;
@Component
public interface UserService {

    User createUser (User user);
    User updateUser(int id, User user);
    void deleteUser(int id);
    void deleteUsers(List<Integer> ids);
    User getUserById(int id);
    List<User> getAllUser();
    List<User> getAllUserByIds(List<Integer> ids);
    Page<User> findUserPaginated(int pageNumber, int pageSize, String sortField, String sortDir);
}
