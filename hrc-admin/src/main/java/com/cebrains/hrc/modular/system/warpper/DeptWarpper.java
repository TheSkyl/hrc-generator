package com.cebrains.hrc.modular.system.warpper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.core.base.warpper.BaseControllerWarpper;
import com.cebrains.hrc.core.util.ToolUtil;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author frank
 * @date 2017年4月25日 18:10:31
 */
public class DeptWarpper extends BaseControllerWarpper {

    public DeptWarpper(Object list) {
        super(list);
    }

    @Override
    public void wrapTheMap(Map<String, Object> map) {

        Integer pid = (Integer) map.get("pid");

        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            map.put("pName", "--");
        } else {
            map.put("pName", ConstantFactory.me().getDeptName(pid));
        }
        // 是否为门店
        Integer isStore = (Integer) map.get("is_store");

        if (isStore==null) {
            map.put("isStoreText", "否");
        } else {
            map.put("isStoreText", ConstantFactory.me().getYesOrNoName(isStore));
        }
        // 收费标准
        Integer priceStandard = (Integer) map.get("price_standard");

        if (isStore==null) {
            map.put("priceStandardName", "--");
        } else {
            map.put("priceStandardName", ConstantFactory.me().getPriceStandard(priceStandard));
        }
    }

}
