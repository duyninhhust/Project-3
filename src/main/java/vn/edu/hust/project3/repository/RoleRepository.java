package vn.edu.hust.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.project3.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
