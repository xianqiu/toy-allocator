package com.example.configs;

import com.example.beans.User;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import java.util.Optional;

import static java.util.concurrent.TimeUnit.MINUTES;

@Component
public class CacheConfig {


    @Component
    public static class GenericCache implements JCacheManagerCustomizer {

        @Override
        public void customize(CacheManager cacheManager) {
            var cacheName = "genericCache";

            // 避免单元测试时重复创建cache
            if(Optional.ofNullable(cacheManager.getCache(cacheName)).isPresent()) return;

            cacheManager.createCache(cacheName, new MutableConfiguration<>()

                    //Set expiry policy.
                    //CreatedExpiryPolicy: Based on creation time
                    //AccessedExpiryPolicy: Based on time of last access
                    //TouchedExpiryPolicy: Based on time of last OR update
                    //ModifiedExpiryPolicy: Based on time of last update
                    //ExternalExpiryPolicy: Ensures the cache entries never expire (default expiry policy)
                    .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(MINUTES, 10)))
                    // store by reference or value.
                    .setStoreByValue(false)
            );
        }
    }

    @Component
    public static class UserCache implements JCacheManagerCustomizer {

        @Override
        public void customize(CacheManager cacheManager) {
            var cacheName = "userCache";

            // 避免单元测试时重复创建cache
            if(Optional.ofNullable(cacheManager.getCache(cacheName)).isPresent()) return;

            cacheManager.createCache(cacheName, new MutableConfiguration<String, User>()
                    .setTypes(String.class, User.class)
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(MINUTES, 10)))
                    .setStoreByValue(true)
            );
        }
    }
}
