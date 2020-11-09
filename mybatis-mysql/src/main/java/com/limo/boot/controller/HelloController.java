package com.limo.boot.controller;

import com.limo.boot.dbservice.DBConsole;
import com.limo.boot.mapper.AdminUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    public DBConsole dbConsole;

    @ResponseBody
    @RequestMapping("/hello/{id}")
    public String helloAdmin(@PathVariable int id) {
        try {
            return dbConsole.select(id).toString();
        } catch (Exception e) {
            logger.error("异常{}", e);
            return "查询失败，请检查参数！";
        }
    }
}
