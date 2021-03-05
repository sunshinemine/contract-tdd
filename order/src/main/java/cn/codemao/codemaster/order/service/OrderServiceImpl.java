package cn.codemao.codemaster.order.service;

import cn.codemao.codemaster.common.entity.Order;
import cn.codemao.codemaster.order.dao.OrderDao;
import cn.codemao.codemaster.order.feign.AccountApi;
import cn.codemao.codemaster.order.feign.StorageApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageApi storageApi;
    @Autowired
    private AccountApi accountApi;

    /**
     * 创建订单
     *
     * @param order
     * @return 测试结果：
     * 1.添加本地事务：仅仅扣减库存
     * 2.不添加本地事务：创建订单，扣减库存
     */
    @Override
    @Transactional
    public void create(Order order) {
        LOGGER.info("------->交易开始");

        //本地方法
        orderDao.create(order);

        //远程方法 扣减库存
        storageApi.decrease(order.getProductId(), order.getCount());

        //远程方法 扣减账户余额
        LOGGER.info("------->扣减账户开始order中");
        accountApi.decrease(order.getUserId(), order.getMoney(), order.getProductId());
        LOGGER.info("------->扣减账户结束order中");
        int i = 1 / 0;
        LOGGER.info("------->交易结束");
    }

    /**
     * 修改订单状态
     */
    @Override
    public void update(Long userId, Long productId, Integer status) {
        LOGGER.info("修改订单状态，入参为：userId={},money={},status={}", userId, productId, status);
        orderDao.update(userId, productId, status);
    }

    @Override
    public Order detail(Order order) {
        return orderDao.detail(order);
    }
}