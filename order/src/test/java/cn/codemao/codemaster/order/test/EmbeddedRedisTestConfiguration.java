package cn.codemao.codemaster.order.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;
import redis.embedded.exceptions.EmbeddedRedisException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Profile("test")
public class EmbeddedRedisTestConfiguration {

    private static RedisServer redisServer;

    public EmbeddedRedisTestConfiguration(@Value("${spring.redis.port}") final int redisPort) {
        redisServer = new RedisServer(redisPort);
    }

    @PostConstruct
    public void startRedis() {
        try {
            redisServer.start();
        } catch (EmbeddedRedisException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void stopRedis() {
        try {
            redisServer.stop();
        } catch (EmbeddedRedisException e) {
            e.printStackTrace();
        }
    }
}