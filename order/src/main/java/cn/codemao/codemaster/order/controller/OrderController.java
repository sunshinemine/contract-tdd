package cn.codemao.codemaster.order.controller;

import cn.codemao.codemaster.common.entity.Order;
import cn.codemao.codemaster.common.result.Back;
import cn.codemao.codemaster.common.result.Result;
import cn.codemao.codemaster.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @PostMapping("/create")
    public Back create(@RequestBody Order order) {
        orderServiceImpl.create(order);
        return Back.noBody(Result.SUCCESS);
    }

    /**
     * 修改订单状态
     *
     * @param userId
     * @param productId
     * @param status
     * @return
     */
    @PutMapping("/update")
    public Back update(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId, @RequestParam("status") Integer status) {
        orderServiceImpl.update(userId, productId, status);
        return Back.noBody(Result.SUCCESS);
    }

    @GetMapping("/detail")
    public Back<Order> getOrder(Order order) {
        return Back.body(Result.SUCCESS, orderServiceImpl.detail(order));
    }
}
