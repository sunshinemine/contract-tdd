package cn.codemao.codemaster.order.service;

import cn.codemao.codemaster.common.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    void create(Order order);

    /**
     * 修改订单状态
     *
     * @param userId
     * @param productId
     * @param status
     */
    void update(Long userId, Long productId, Integer status);

    Order detail(Order order);
}
