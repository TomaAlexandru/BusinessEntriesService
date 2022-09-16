package com.localsearch.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CacheService {
    Jedis jedis;
    private final Logger logger = LogManager.getLogger(CacheService.class);
    private final int KEY_TIME_TO_LEAVE_SECONDS = 10;

    @PostConstruct
    private void init() {
       this.jedis = new Jedis("0.0.0.0", 6379);
        logger.info("Successfully connected to redis instance");
    }

    public void setKeyValue(String key, String value) {
        jedis.set(key, value);
        jedis.expire(key, KEY_TIME_TO_LEAVE_SECONDS);
    }

    public Optional<String> getKeyValue(String key) {
        return Optional.ofNullable(jedis.get(key));
    }
}
