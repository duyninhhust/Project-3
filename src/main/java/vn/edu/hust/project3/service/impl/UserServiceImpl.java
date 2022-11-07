package vn.edu.hust.project3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.hust.project3.exception.CategoryNotFoundException;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.model.User;
import vn.edu.hust.project3.repository.UserRepository;
import vn.edu.hust.project3.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        User rs = findUserById(id);
        rs.setRole(user.getRole());
        rs.setPassword(user.getPassword());
        rs.setEmail(user.getEmail());
        rs.setFirstName(user.getFirstName());
        rs.setLastName(user.getLastName());
        return userRepository.save(rs);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteUsers(List<Integer> ids) {
        userRepository.deleteByIdIn(ids);
    }

    @Override
    public User getUserById(int id) {
        return findUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUserByIds(List<Integer> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public Page<User> findUserPaginated(int pageNumber, int pageSize, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return userRepository.findAll(pageable);
    }

    private User findUserById(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new CategoryNotFoundException(String.format("Could not found user with ID= [%s]", id));
        }
        return optional.get();
    }
}
