package com.cebrains.hrc;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * HRC Web程序启动类
 *
 * @author frank
 * @date 2017-05-21 9:43
 */
public class HRCServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HRCApplication.class);
    }

}
