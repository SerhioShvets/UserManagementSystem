package com.management.system.repositories;

import com.management.system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Transactional
    void removeUserByUsername(String username);
}
