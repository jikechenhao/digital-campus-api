import com.hwedu.bookcross.dao.IBookDAO;
import com.hwedu.bookcross.dao.IGroupDAO;
import com.hwedu.bookcross.service.impl.GroupService;
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

import javax.annotation.Resource;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-main.xml")
@WebAppConfiguration()
public class BookGroupDaoTest {
    // 非业务
    @Resource
    private WebApplicationContext wac;
    private MockMvc mkm;
    // 测试dao
    @Resource
    private IGroupDAO groupDAO;
    @Resource
    private GroupService groupService;
    @Resource
    private IBookDAO bookDAO;

    @Before
    public void init() {
        mkm = MockMvcBuilders.webAppContextSetup(wac).build();
    }





/*	@Test
    public void deleteAndUpdateById() {
		GroupDTO gd = new GroupDTO();
		gd.setId("6efeb65a-fd3d-11e5-8d21-cfce1f1d4e5f");
		gd.setName("第八十一套");
		gd.setIsDeleted(false);
		groupDAO.deleteAndUpdateById(gd);
	}

	@Test
	public void queryRecordCount() {
		Integer count = groupDAO.queryRecordCount();
		System.out.println(count);
	}

	// 测试查询一套书下所有图书信息
	@Test
	public void queryGroupIncludeBookList() {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId("6f12ae26-fd3d-11e5-8d2d-dbc4da2e15bd");
		GroupVO gv = groupDAO.queryGroupIncludeBookList(groupDTO);
		System.out.println(gv);
	}*/

//	@Test
//	public void updateAndDeleteByBookId() {
//		BookDTO dto = new BookDTO();
//		dto.setId("6239642a-fd37-11e5-9c37-a36a6e094944");
//		// dto.setIsDeleted(true);
//		dto.setPress("儿童教育出版社");
//		bookDAO.updateAndDeleteByBookId(dto);
//	}

    // 测试controller
    @Test
    public void saveGroupTest() throws Exception {
        /*
         * mkm.perform(MockMvcRequestBuilders.get("/group/0/测试添加套系").
		 * characterEncoding("UTF-8"))
		 * .andDo(MockMvcResultHandlers.print()).andReturn();
		 */
        mkm.perform(MockMvcRequestBuilders.get("/group/0/").characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void queryAllGroupController() throws Exception {
        mkm.perform(MockMvcRequestBuilders.get("/m/cross/group/1/10").characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void deleteAndUpdateByIdController() throws Exception {
        mkm.perform(
                MockMvcRequestBuilders.get("/group/2/6f0860e2-fd3d-11e5-8d27-d7c1a78641dc").characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void queryGroupIncludeBookListController() throws Exception {
        mkm.perform(MockMvcRequestBuilders.get("/m/cross/book/1/718b57ac-fd3d-11e5-8ee1-ffbfd87f018c")
                .characterEncoding("UTF-8")).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void deleteBookAssociateGroupController() throws Exception {
        mkm.perform(MockMvcRequestBuilders
                .get("/m/cross/book/2/631603c6-fd37-11e5-9cde-43155bba7956/6ee9a08a-fd3d-11e5-8d1d-5317dcf3565f")
                .characterEncoding("UTF-8")).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void updateAndDeleteByBookIdController() throws Exception {
        mkm.perform(MockMvcRequestBuilders.get("/m/cross/book/2/6239642a-fd37-11e5-9c37-a36a6e094944")).andDo(MockMvcResultHandlers.print()).andReturn();
    }
}
