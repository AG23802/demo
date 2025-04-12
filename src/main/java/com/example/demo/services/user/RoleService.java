package com.example.demo.services.user;

import com.example.demo.entities.Role;
import com.example.demo.repositories.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
}
