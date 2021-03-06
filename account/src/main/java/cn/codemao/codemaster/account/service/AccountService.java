package cn.codemao.codemaster.account.service;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 扣减账户余额
     * test
     * @param userId 用户id
     * @param money  金额
     */
    void decrease(Long userId, BigDecimal money, Long productId);
}
