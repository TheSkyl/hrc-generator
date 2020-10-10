package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.MemberSettlement;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class MemberSettlementWrapper extends BaseCustomWarpper<MemberSettlement> {

    public MemberSettlementWrapper(List<MemberSettlement> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(MemberSettlement item) {
        String memberName = ConstantFactory.me().getMemberNameByTreatment(item.getTreatment());
        if(StringUtils.isEmpty(memberName)){
            memberName = ConstantFactory.me().getMemberName(item.getMember());
        }
        item.setMemberName(memberName);
        List<String> pns = ConstantFactory.me().getProjectNamesByTreatment(item.getTreatment());
        if (pns != null && pns.size() > 0) {
            item.setProjectNames(String.join(" , ", pns));
        }
        List<Map<String, Object>> products = ConstantFactory.me().getProductNamesBySettlement(item.getId());
        if(products!=null) {
            String pds = products.stream().map(p -> p.get("name").toString().concat("（").concat(String.valueOf(p.get("amount"))).concat("个）")).collect(Collectors.joining("；"));
            item.setProductNames(pds);
        }else{
            item.setProductNames("未结算康护产品");
        }
        item.setPaymentMethodName(ConstantFactory.me().getPaymentMethodName(item.getPaymentMethod()));
//        item.setStatusName(ConstantFactory.me().getMemberSettlementStatusName(item.getStatus()));
    }

}
