package org.wangjj.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.wangjj.practice.startup.BootTestApplication;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * ClassName: MocMvcTest <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/8/7 下午5:54 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = BootTestApplication.class)
//@AutoConfigureMockMvc
public class MocMvcTest {

    private final Logger logger = LoggerFactory.getLogger(MockMvc.class);

    @Autowired
    MockMvc mockMvc;

    @Before
    public void testBefore() {
        logger.info("before");
    }

    @Test
    public void testController() throws Exception {
        ResultMatcher resultMatcher = new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                mvcResult.getResponse();
            }
        };
        mockMvc.perform(MockMvcRequestBuilders.post("/test/add").content("thisismockmvc"))
                .andDo(print()).andExpect(jsonPath("$.test").value(33));
        //logger.info(ret);
    }

    @After
    public void testAfter() {
        logger.info("after");
    }
}
