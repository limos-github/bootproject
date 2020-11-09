package com.limo.boot.dbservice;

import com.limo.boot.entity.AdminUser;
import com.limo.boot.mapper.AdminUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class DBConsole {
    private static final Logger logger = LoggerFactory.getLogger(DBConsole.class);
    @Autowired
    AdminUserMapper adminUserMapper;
    Lock lock = new ReentrantLock();

    public synchronized String reduce(int id){
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
        int a = adminUser.getAge();
        if (a > 0) {
            adminUser.setAge(a - 1);
            adminUserMapper.updateByPrimaryKey(adminUser);
            logger.info(Thread.currentThread().getName() + " 获得了控制权 开始修改年龄为 " + adminUser.getAge());
            return "管理员：" + adminUser.getUserName() + " 当前年龄为 ：" + adminUser.getAge();
        } else {
            return "管理员：" + adminUser.getUserName() + " 当前年龄为 ：" + adminUser.getAge() + "年龄已无法修改";
        }
    }

    public String add(int id) {
        lock.lock();
        try {
            AdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
            int a = adminUser.getAge();
            adminUser.setAge(a + 1);
            adminUserMapper.updateByPrimaryKey(adminUser);
            logger.info(Thread.currentThread().getName() + " 获得了控制权 开始修改年龄为 " + adminUser.getAge());
            return "管理员：" + adminUser.getUserName() + " 当前年龄为 ：" + adminUser.getAge();
        } catch (Exception e) {
            logger.info(Thread.currentThread().getName() + " 获得了控制权 修改失败");
            return Thread.currentThread().getName() + " 获得了控制权 但是修改失败";
        } finally {
            lock.unlock();
        }
    }

    public AdminUser select(int id) {
        logger.info("查询取得数据!");
        return adminUserMapper.selectByPrimaryKey(id);
    }


}
