package com.eugene.controller;

import com.eugene.common.Constants;
import com.eugene.exception.ErrorCode;
import com.eugene.exception.RestException;
import com.eugene.model.Admin;
import com.eugene.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by DCLab on 12/17/2015.
 */
@RestController
@Api(value = "管理员模块")
@RequestMapping(Constants.URI_API_BASE + "/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "获取所有管理员")
    @RequestMapping(method = RequestMethod.GET)
    public List<Admin> getAdmins(){
        return adminService.getAdmins();
    }

    @ApiOperation(value = "根据id获取管理员")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Admin getAdminById(@PathVariable("id") int id){
        Admin admin = adminService.getAdminById(id);
        if (admin == null)
            throw new RestException("没有找到id=" + id + "的管理员", ErrorCode.NOT_FOUND, (long)id);
        return admin;
    }

    @ApiOperation(value = "添加管理员")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 409, message = "CONFLICT")})
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Admin insertAdminSelective(@RequestBody Admin admin, HttpServletResponse response){
        int modified = adminService.insertAdminSelective(admin);
        if (modified != 0){
            int id = admin.getId();
            response.setHeader("Location", "/admins/" + id);
            return admin;
        } else {
            throw new RestException("添加新管理员产生冲突", ErrorCode.CONFLICT);
        }
    }

    @ApiOperation(value = "根据id选择性更新管理员") @ApiResponse(code = 404, message = "NOT_FOUND")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAdminByIdSelective(@RequestBody Admin admin){
        int modified = adminService.updateAdminByIdSelective(admin);
        if (modified != 0){
            return;
        } else {
            throw new RestException("没有找到此id的管理员，无法更新", ErrorCode.NOT_FOUND, (long)admin.getId());
        }
    }

    @ApiOperation(value = "根据id删除管理员")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdminById(@PathVariable("id") int id){
        int modified = adminService.deleteAdminById(id);
        if (modified != 0){
            return;
        } else {
            throw new RestException("没有找到此id的管理员，无法删除", ErrorCode.NOT_FOUND, (long)id);
        }
    }


}
