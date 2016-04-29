package com.hwedu.test;

import com.hwedu.dao.IFlowerGroupDao;
import com.hwedu.po.FlowerGroupPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@WebAppConfiguration(value = "src/main/webapp")
public class FlowerGroupDaoTest {
    @Resource
    private IFlowerGroupDao groupDao;

    @Test
    public void insertOneGroup() {
        FlowerGroupPO gp = new FlowerGroupPO();
        gp.setClassId("948eeedc-ff8e-11e5-af71-4fbd8c703c62");
        gp.setName("第三学习小组");
        int count = groupDao.insertOneGroup(gp);
        System.out.println(gp.getId());
        System.out.println(count);
    }
}
