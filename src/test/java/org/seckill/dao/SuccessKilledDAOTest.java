package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by lishaoxun on 2016/6/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class SuccessKilledDAOTest {

    @Resource
    private SuccessKilledDAO successKilledDAO;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id=1000L;
        long userPhone=15894562291L;
        int insertCount = successKilledDAO.insertSuccessKilled(id,userPhone);
        System.out.println("insertCount="+insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = successKilledDAO.queryByIdWithSeckill(1000L);
        System.out.println(successKilled.getSeckill());
    }
}