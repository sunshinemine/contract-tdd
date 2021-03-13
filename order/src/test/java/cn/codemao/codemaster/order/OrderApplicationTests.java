package cn.codemao.codemaster.order;

import cn.codemao.codemaster.common.entity.Order;
import cn.codemao.codemaster.order.dao.OrderDao;
import cn.codemao.codemaster.order.service.OrderServiceImpl;
import cn.codemao.codemaster.order.test.TruncateDataListener;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private RedisTemplate<String, Object> objectRedisTemplate;

    @Test
    public void mockDao() {
        Order order = new Order();
        order.setId(1L);
        orderService.detail(order);
        OrderDao mock = Mockito.spy(OrderDao.class);
        when(mock.detail(any())).thenReturn(any());
    }

    @Test
    public void redis() {
        objectRedisTemplate.opsForValue().set("test", 21);
        System.out.println("test:" + objectRedisTemplate.opsForValue().get("test"));
    }

    @Test
    @Sql({"/sql/insert_order.sql"})
    public void testDB() {
        Order order = new Order();
        order.setId(1L);
        orderService.detail(order);
    }

}
