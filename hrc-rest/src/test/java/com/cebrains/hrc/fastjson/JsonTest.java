package com.cebrains.hrc.fastjson;

import com.alibaba.fastjson.JSON;
import com.cebrains.hrc.core.util.MD5Util;
import com.cebrains.hrc.rest.common.SimpleObject;
import com.cebrains.hrc.rest.modular.auth.converter.BaseTransferEntity;

/**
 * json测试
 *
 * @author frank
 * @date 2017-08-25 16:11
 */


public class JsonTest {

    public static void main(String[] args) {
        String randomKey = "1xm7hw";

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setUser("fsn");
        baseTransferEntity.setObject("123123");

        String json = JSON.toJSONString(simpleObject);

        //md5签名
        String encrypt = MD5Util.encrypt(json + randomKey);
        baseTransferEntity.setSign(encrypt);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
