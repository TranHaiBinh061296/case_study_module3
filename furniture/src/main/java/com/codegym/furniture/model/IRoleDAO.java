package com.codegym.furniture.model;

import com.codegym.furniture.view.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDAO {
    public void inserRole(Role role) throws SQLException;

    public Role selectRole(int id);

    public List<Role> selectAllRole();

    public boolean deleteRole(int id) throws SQLException;

    public boolean updateRole(Role role) throws SQLException;
}
