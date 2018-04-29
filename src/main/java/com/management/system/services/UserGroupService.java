package com.management.system.services;

import com.management.system.repositories.UserRepository;

public interface UserGroupService {

    void removeUserFromGroup(String username, String groupname);
}
