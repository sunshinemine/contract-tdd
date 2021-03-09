package cn.codemao.codemaster.order;

import cn.codemao.codemaster.common.entity.Order;
import cn.codemao.codemaster.order.dao.OrderDao;
import cn.codemao.codemaster.order.service.OrderServiceImpl;
import cn.codemao.codemaster.order.test.TruncateDataListener;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.mockito.Mockito.when;

@FlywayTest
@ActiveProfiles("test")
@SpringBootTest(classes = OrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TruncateDataListener.class,
        FlywayTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class
})
public class OrderApplicationTests {

    @SpyBean
    private OrderServiceImpl orderService;

    @Autowired
    private OrderDao orderDao;

    @Test
    public void contextLoads() {
        when(orderService.test(1)).thenReturn(3);
    }

    @Test
    public void really() {
        int test = orderService.test(2);
        Assert.assertEquals(3, test);
    }

    @Test
    @Sql({"/sql/insert_order.sql"})
    public void testDB() {
        Order order = new Order();
        order.setId(1L);
        orderDao.detail(order);
    }

}
