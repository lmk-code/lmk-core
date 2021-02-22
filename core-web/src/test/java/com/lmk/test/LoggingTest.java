package com.lmk.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志测试
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class LoggingTest {

    @Test
    public void testLogging(){
        Logger logger = LoggerFactory.getLogger(LoggingTest.class);
        logger.warn("这是警告");
    }

}
