package com.liwy.project.dao;

import java.util.List;

import com.liwy.project.entity.Role;

public interface IRoleDao {

    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();
}
