package com.cebrains.hrc.common.constant.dictmap.factory;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.exception.BizExceptionEnum;
import com.cebrains.hrc.core.exception.HRCException;

import java.lang.reflect.Method;

/**
 * 字段的包装创建工厂
 *
 * @author frank
 * @date 2017-05-06 15:12
 */
public class DictFieldWarpperFactory {

    public static Object createFieldWarpper(Object field, String methodName) {
        IConstantFactory me = ConstantFactory.me();
        try {
            Method method = IConstantFactory.class.getMethod(methodName, field.getClass());
            Object result = method.invoke(me, field);
            return result;
        } catch (Exception e) {
            try {
                Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
                Object result = method.invoke(me, Integer.parseInt(field.toString()));
                return result;
            } catch (Exception e1) {
                throw new HRCException(BizExceptionEnum.ERROR_WRAPPER_FIELD);
            }
        }
    }

}
