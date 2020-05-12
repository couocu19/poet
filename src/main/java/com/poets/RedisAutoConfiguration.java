package com.poets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisAutoConfiguration {
    /**
     * 重写Redis序列化方式,使用json方式;
     * 当我们的数据存储到Redis时，我们的键和值都是通过String提供的Serializer序列化到Redis的。
     * RedisTemplate默认使用的是JdkSerializationRedisSerializer
     * StringRedisTemplate默认使用的是StringRedisSerializer
     * Spring Data JPA为我们提供了下面的Serializer
     * GenericToStringSerializer，Jackson2JsonRedisSerializer
     * 。。。
     * OxmSerializer,StringRedisSerializer
     * 在此我们将自己配置RedisTemplate并定义Serializer
     * @param redisConnectionFactory
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
       //创建一个json的序列化对象
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //设置value的序列化方式json
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
       //设置key的序列化方式为string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
       //设置hash key序列化方式为string
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置hash value的序列化方式也为json
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}