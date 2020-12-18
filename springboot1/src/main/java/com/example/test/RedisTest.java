package com.example.test;

import com.example.dao.RedisDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    public static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    public RedisDao redisDao;
    @Autowired
    public StringRedisTemplate template;

    @Test
    public void contextLoads(){

    }

    @Test
    public void testRedis(){
//        redisDao.setKey("key5","321cba");
//        logger.info(redisDao.getValue("key5"));
        try{
            template.opsForValue().set("key5","321cba");
        }catch (Exception e) {
            e.printStackTrace();
        }

        //Assert.assertEquals("321cba",template.opsForValue().get("key5"));
    }
}
