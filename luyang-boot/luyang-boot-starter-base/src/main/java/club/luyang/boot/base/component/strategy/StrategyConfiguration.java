package club.luyang.boot.base.component.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author lu_yang
 */
@Configuration
public class StrategyConfiguration {

    @Bean
    public StrategySelector strategySelector(List<StrategyHandler<?, ?>> strategyHandlers) {
        StrategySelector strategySelector = new StrategySelector();
        strategySelector.set(strategyHandlers);
        return strategySelector;
    }
}
