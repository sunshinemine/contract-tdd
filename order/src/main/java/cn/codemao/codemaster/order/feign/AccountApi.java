package cn.codemao.codemaster.order.feign;

import cn.codemao.codemaster.common.result.Back;
import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account")
public interface AccountApi {

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     * @return
     */
    @RequestMapping(value = "/account/decrease", method = RequestMethod.GET)
//    @GetMapping("/account/decrease")
    Back decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("productId") Long productId);
}
