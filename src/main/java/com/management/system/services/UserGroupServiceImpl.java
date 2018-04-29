package com.management.system.services;

import com.management.system.models.Group;
import com.management.system.models.User;
import com.management.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void removeUserFromGroup(String username, String groupname) {
        User user = userRepository.findByUsername(username);
        Set<Group> groups = user.getUser_group();
        groups.removeIf(g -> g.getGroupname().equals(groupname));
        user.setUser_group(groups);
        userRepository.save(user);
    }
}
