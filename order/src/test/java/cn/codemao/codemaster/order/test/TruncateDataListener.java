package cn.codemao.codemaster.order.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.sql.SQLException;

public class TruncateDataListener extends AbstractTestExecutionListener {

    public int getOrder() {
        return 4500;
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws SQLException {
        TruncateDatabaseService truncateDatabaseService = (TruncateDatabaseService) testContext.getApplicationContext().getBean("TruncateDatabaseService");
        truncateDatabaseService.truncate();
    }
}
