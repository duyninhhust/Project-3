package vn.edu.hust.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.project3.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);

}
