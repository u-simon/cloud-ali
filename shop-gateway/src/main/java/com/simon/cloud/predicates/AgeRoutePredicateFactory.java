package com.simon.cloud.predicates;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author simon
 * @date 2020/10/8 5:14 下午
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {


    public AgeRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("minAge", "maxAge");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            String ageStr= serverWebExchange.getRequest().getQueryParams().getFirst("age");
            if (StringUtils.isNotBlank(ageStr)){
                int age = Integer.parseInt(ageStr);
                if (age > config.getMinAge() && age < config.getMaxAge()){
                    return true;
                }
            }
            return false;
        };
    }


    @Data
    public static class Config {
        private int minAge;

        private int maxAge;
    }
}
