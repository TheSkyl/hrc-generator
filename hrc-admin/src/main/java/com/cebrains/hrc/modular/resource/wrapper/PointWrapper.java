package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Point;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class PointWrapper extends BaseCustomWarpper<Point> {

    public PointWrapper(List<Point> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(Point item) {
        item.setMemberName(ConstantFactory.me().getMemberName(item.getMember()));
        item.setTypeName(ConstantFactory.me().getPointTypeName(item.getType()));
    }

}
