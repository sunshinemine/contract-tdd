package cn.codemao.codemaster.account.controller;

import cn.codemao.codemaster.account.service.AccountService;
import cn.codemao.codemaster.common.result.Back;
import cn.codemao.codemaster.common.result.Result;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountServiceImpl;

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     * @return
     */
    @GetMapping("/decrease")
    public Back decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("productId") Long productId) {
        accountServiceImpl.decrease(userId, money, productId);
        return Back.noBody(Result.SUCCESS);
    }
}
