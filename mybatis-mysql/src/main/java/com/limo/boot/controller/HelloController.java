package com.limo.boot.controller;

import com.limo.boot.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    public AdminUserMapper adminUserMapper;

    @ResponseBody
    @RequestMapping("/hello/{id}")
    public String helloAdmin(@PathVariable int id){
        try {
            return adminUserMapper.selectByPrimaryKey(id).toString();
        }catch (Exception e){
            return "查询失败，请检查参数！";
        }
    }
}
