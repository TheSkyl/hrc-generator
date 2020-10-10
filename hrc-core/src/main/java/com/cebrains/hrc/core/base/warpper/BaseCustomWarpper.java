package com.cebrains.hrc.core.base.warpper;

import java.util.List;
import java.util.Map;

/**
 * 控制器查询结果的包装类基类
 *
 * @author frank
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BaseCustomWarpper<T> {

    public Object obj = null;

    public BaseCustomWarpper(Object obj) {
        this.obj = obj;
    }

    @SuppressWarnings("unchecked")
    public Object wrap() {
        if (this.obj instanceof List) {
            List<T> list = (List<T>) this.obj;
            for (T t : list) {
                wrapTheObject(t);
            }
            return list;
        } else if (this.obj instanceof Object) {
            wrapTheObject((T) this.obj);
            return this.obj;
        } else {
            return this.obj;
        }
    }

    protected abstract void wrapTheObject(T map);
}
