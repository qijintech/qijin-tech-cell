package tech.qijin.cell.counting.config;

import org.springframework.core.Ordered;
import org.springframework.retry.annotation.RetryConfiguration;

public class CellCountingRetryConfiguration extends RetryConfiguration {
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
