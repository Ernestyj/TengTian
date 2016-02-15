package com.eugene.service;

import com.eugene.mapper.AdminMapper;
import com.eugene.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DCLab on 12/17/2015.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAdmins() {
        return adminMapper.getAdmins();
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByAccount(String name) {
        return adminMapper.getAdminByAccount(name);
    }

    @Override
    public int updateAdminByIdSelective(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int deleteAdminById(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertAdminSelective(Admin admin) {
        return adminMapper.insertSelective(admin);
    }

}
