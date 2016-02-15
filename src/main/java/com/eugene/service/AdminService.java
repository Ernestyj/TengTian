package com.eugene.service;

import com.eugene.model.Admin;

import java.util.List;

/**
 * Created by DCLab on 12/17/2015.
 */
public interface AdminService {

    List<Admin> getAdmins();
    Admin getAdminById(Integer id);
    Admin getAdminByAccount(String name);

    int updateAdminByIdSelective(Admin admin);

    int deleteAdminById(Integer id);

    int insertAdminSelective(Admin admin);
}
