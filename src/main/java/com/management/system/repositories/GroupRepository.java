package com.management.system.repositories;

import com.management.system.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface GroupRepository extends JpaRepository<Group, Long>{
    Group findByGroupname(String groupname);
    @Transactional
    void deleteByGroupname(String groupname);
}
