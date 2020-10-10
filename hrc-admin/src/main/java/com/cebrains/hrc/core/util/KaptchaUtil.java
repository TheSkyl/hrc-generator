package com.cebrains.hrc.core.util;

import com.cebrains.hrc.config.properties.HRCProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     *
     * @author frank
     * @Date 2017/5/23 22:34
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(HRCProperties.class).getKaptchaOpen();
    }
}