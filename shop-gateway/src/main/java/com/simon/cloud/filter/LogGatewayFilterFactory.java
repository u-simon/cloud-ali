package com.simon.cloud.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author simon
 * @date 2020/10/8 5:41 下午
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (config.isCacheLog()){
                System.out.println("cacheLog已经开启了...");
            }
            if (config.isConsoleLog()){
                System.out.println("consoleLog已经开启了...");
            }
            return chain.filter(exchange);
        };
    }



    @Data
    public static class Config {
        private boolean consoleLog;

        private boolean cacheLog;
    }
}
