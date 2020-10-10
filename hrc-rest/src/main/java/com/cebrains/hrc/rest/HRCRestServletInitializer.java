package com.cebrains.hrc.rest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * HRC REST Web程序启动类
 *
 * @author frank
 * @date 2017年9月29日09:00:42
 */
public class HRCRestServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HRCRestApplication.class);
    }

}
