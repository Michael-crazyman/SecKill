package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lishaoxun on 2016/6/19.
 */

/**
 *
 * 配置spring和junit整合
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDAOTest {

    @Resource
    private SeckillDAO seckillDAO;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDAO.reduceNumber(1000L,killTime);
        System.out.println(updateCount);
    }

    @Test
    public void queryById() throws Exception {
        int id=1000;
        SecKill s = seckillDAO.queryById(id);
        System.out.println(s.getName());
    }

    @Test
    public void queryAll() throws Exception {
        List<SecKill> secKills =  seckillDAO.queryAll(0,100);
        for(SecKill s : secKills){
            System.out.println(s);
        }
    }
}