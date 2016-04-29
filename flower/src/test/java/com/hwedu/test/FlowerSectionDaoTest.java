package com.hwedu.test;

import com.hwedu.dao.IFlowerSectionDao;
import com.hwedu.po.FlowerSectionPO;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@WebAppConfiguration(value = "src/main/webapp")
public class FlowerSectionDaoTest {
    @Resource
    private IFlowerSectionDao flowerSectionDao;

    @Test
    public void insertOneSection() throws ParseException {
        FlowerSectionPO fsp = new FlowerSectionPO();
        fsp.setName("第七节");
        fsp.setBeginTime(DateUtils.parseDate("15:55:00", "hh:mm:ss"));
        fsp.setEndTime(DateUtils.parseDate("16:35:00", "hh:mm:ss"));
        flowerSectionDao.insertOneSection(fsp);
    }
}
