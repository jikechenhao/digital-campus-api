import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.task.CrossTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * 测试工具
 * Created by lizhiqiang on 2016/4/15.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-main.xml")
@WebAppConfiguration()
public class TestTool {

    private Jedis jedis = new Jedis(Variables.getInstance().getRedisIp(), Variables.getInstance().getRedisPort());
    // 非业务
    @Resource
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Resource
    private CrossTask crossTask;


    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //testDataPreprocessor();//测试用数据
    }


    @Test
    public void test() {
        //crossTask.cross();
        System.out.println(Variables.getInstance().getExcelDataMap());
    }

    /**
     * 测试数据预处理器
     */
    private void testDataPreprocessor() {
        jedis.set("term_id", "72d361d0-ff96-11e5-bfad-d37c3cf7426c");
        jedis.set("d5289376-ff84-11e5-97bf-a77a14afffde",
                "[\"948eeedc-ff8e-11e5-af71-4fbd8c703c62\", \"96b4691c-ff8e-11e5-af72-074550af753b\", \"988b99ae-ff8e-11e5-af73-472238133d3d\", \"99f5cb16-ff8e-11e5-af74-ef10df040c95\", \"9dd4e050-ff8e-11e5-af75-4b592abd5b54\", \"a08f49d4-ff8e-11e5-af76-a7945ec42876\", \"a30c2e0c-ff8e-11e5-af77-7b163405cea9\", \"a61255ae-ff8e-11e5-af78-e7f0054782cb\", \"a20a74ae-ff8f-11e5-af7a-b326d0dc81c4\", \"a610be3c-ff8f-11e5-af7b-7fef56a67b1b\", \"a887d560-ff8f-11e5-af7c-d7ed2d582671\", \"abf84798-ff8f-11e5-af7d-9f29e90abfe6\"]");
    }

    // 测试controller
    @Test
    public void testController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/m/cross/book/2/6239642a-fd37-11e5-9c37-a36a6e094944")).andDo(MockMvcResultHandlers.print()).andReturn();
    }
}
