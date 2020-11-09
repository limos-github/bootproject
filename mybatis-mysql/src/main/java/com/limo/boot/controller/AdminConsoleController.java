package com.limo.boot.controller;

import com.limo.boot.dbservice.DBConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminConsoleController {
    private static final Logger logger = LoggerFactory.getLogger(AdminConsoleController.class);
    @Autowired
    public DBConsole dbConsole;

    @ResponseBody
    @RequestMapping("/reduce/{id}")
    public String reduce(@PathVariable int id) {
        try {
            logger.info("查询取得数据!");
            return dbConsole.reduce(id);
        } catch (Exception e) {
            logger.error("异常{}", e);
            return "查询失败，请检查参数！";
        }
    }

    @ResponseBody
    @RequestMapping("/add/{id}")
    public String add(@PathVariable int id) {
        try {
            logger.info("查询取得数据!");
            return dbConsole.add(id);
        } catch (Exception e) {
            logger.error("异常{}", e);
            return "查询失败，请检查参数！";
        }
    }
}
