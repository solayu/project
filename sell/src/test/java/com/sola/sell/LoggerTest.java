package com.sola.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest{
    private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void testlogger(){
        String name="root";
        String password="root";
        logger.error("错误");
        logger.debug("debug---");
        logger.info("name:{},password:{}",name,password);
    }

}
