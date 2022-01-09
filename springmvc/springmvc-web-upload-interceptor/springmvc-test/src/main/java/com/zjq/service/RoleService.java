package com.zjq.service;

import com.zjq.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> list() ;

    void save(Role role);
}
