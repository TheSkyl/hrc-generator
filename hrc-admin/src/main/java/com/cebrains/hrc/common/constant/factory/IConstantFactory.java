package com.cebrains.hrc.common.constant.factory;

import com.cebrains.hrc.common.persistence.model.Dict;
import com.cebrains.hrc.common.persistence.model.Member;
import com.cebrains.hrc.common.persistence.vo.KeyAndValueVo;

import java.util.List;
import java.util.Map;

/**
 * 常量生产工厂的接口
 *
 * @author frank
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    String DICT_KEY_APPT = "预约类型";
    String DICT_KEY_MSC = "产品供应商类别";
    String DICT_KEY_GNDR = "性别";
    String DICT_KEY_YON = "是否状态";
    String DICT_KEY_BLT = "血型";
    String DICT_KEY_PNT= "喜欢的护理时间";
    String DICT_KEY_MST= "婚姻状况";
    String DICT_KEY_PROJC= "项目类别";
    String DICT_KEY_APPST= "预约状态";
    String DICT_KEY_MEML = "会员等级";
    String DICT_KEY_LEVST = "会员等级标准";
    String DICT_KEY_DEVST = "设备状态";
    String FIELD_SHOP = "SH";
    String DICT_KEY_PRCS = "收费标准";
    String DICT_KEY_PAYM = "支付方式";
    String DICT_KEY_MEMD = "会员折扣";
    String DICT_KEY_TMTST = "康护状态";
    String DICT_KEY_LEVPR = "充值赠额";
    String DICT_KEY_MCN = "会员卡名";
    String DICT_KEY_OSPROJ = "申请项目";
    String DICT_KEY_OSSTS = "特殊申请状态";
    String DICT_KEY_OS_SAE_STS = "特殊申请执行状态";
    String DICT_KEY_OSSTST = "外协订单状态";
    String DICT_KEY_PNTT = "积分类型";
    String DICT_KEY_FEFCT = "最终效果";
    String DICT_KEY_CSTFCT = "客户满意度";

    String PERMISSION_DELETE_OTHER_DEPT_MEMBER = "/member/deleteOtherDeptMember";
    String PERMISSION_APPOINTMENT_FOR_OTHER_DEPT = "/appointment/appointmentForOtherDept";
    String PERMISSION_APPOINTMENT_LIST_OTHER_DEPT = "/appointment/listOtherDept";
    String PERMISSION_TREATMENT_LIST_OTHER_DEPT = "/treatment/listOtherDept";
    String PERMISSION_MEMBER_SETTLEMENT_LIST_OTHER_DEPT = "/memberSettlement/listOtherDept";
    String PERMISSION_DEVICE_LIST_OTHER_DEPT = "/device/listOtherDept";
    String PERMISSION_DEVICE_EDIT_OTHER_DEPT = "/device/editOtherDept";
    String PERMISSION_CONSUMABLE_LIST_OTHER_DEPT = "/consumable/listOtherDept";
    String PERMISSION_CONSUMABLE_EDIT_OTHER_DEPT = "/consumable/editOtherDept";
    String PERMISSION_HR_EDIT_OTHER_DEPT = "/memberHealthRecord/editOtherDept";
    String PERMISSION_OS_EDIT_OTHER_DEPT = "/specialApplication/editOtherDept";
    String PERMISSION_OS_APPROVE = "/specialApplication/approve";
    String PERMISSION_RP_EDIT_OTHER_DEPT = "/rehabilitationProgram/editOtherDept";
    String PERMISSION_OUTSOURCE_DELIVER = "/outsourceOrder/deliver";
    String PERMISSION_OUTSOURCE_SETTLE = "/outsourceOrder/settle";

    /**
     * 根据用户id获取用户名称
     *
     * @author frank
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author frank
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 获取区域名称
     */
    String getRegionName(Integer regionId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);

    /**
     * 获取项目字典名称
     * @param category
     * @return
     */
    String getProjectCategory(Integer category);

    /**
     * 获取产品供应商字典名称
     * @param category
     * @return
     */
    String getMedicineSupplierCategory(Integer category);

    /**
     * 获取血型名称
     * @param bloodType
     * @return
     */
    String getBloodTypeName(Integer bloodType);

    /**
     * 婚姻状况名称
     * @param maritalStatus
     * @return
     */
    String getMaritalStatusName(Integer maritalStatus);

    /**
     * 是否有孩子名称
     * @param haveChildren
     * @return
     */
    String getYesOrNoName(Integer haveChildren);

    /**
     * 喜欢的护理时间字典名称
     * @param preferredNursingTime
     * @return
     */
    String getPreferredNursingTimeName(Integer preferredNursingTime);

    List<KeyAndValueVo> findAllProject();

    /**
     * 会员姓名
     * @param memberId
     * @return
     */
    String getMemberName(Integer memberId);

    /**
     * 会员电话号码
     * @param member
     * @return
     */
    String getMemberPhone(Integer member);

    /**
     * 预约类型
     * @param type
     * @return
     */
    String getAppointmentTypeName(Integer type);

    /**
     * 项目名称
     * @param project
     * @return
     */
    String getProjectName(Integer project);

    /**
     * 根据KEY查找字典
     * @param key
     * @return
     */
    List<Dict> findDictByKey(String key);

    /**
     * 预约类型名称
     * @param status
     * @return
     */
    String getAppointmentStatusName(Byte status);

    /**
     * 会员等级显示名称
     * @param level
     * @return
     */
    String getMemberLevelName(Integer level);

    /**
     * 获取会员等级标准
     */
    List<Dict> getLevelStandardDicts();

    /**
     * 获取会员卡号
     * @param card
     * @return
     */
    String getMembershipCardNumber(Integer card);

    /**
     * 获取设备状态名称
     *
     * @param status
     * @return
     */
    String getDeviceStatus(Integer status);

    /**
     * 获取价格标准
     * @param priceStandard
     * @return
     */
    String getPriceStandard(Integer priceStandard);

    /**
     * 根据康护记录获取会员姓名
     * @param treatment
     * @return
     */
    String getMemberNameByTreatment(Integer treatment);

    /**
     * 根据康护记录查询康护项目名称
     * @param treatment
     * @return
     */
    List<String> getProjectNamesByTreatment(Integer treatment);

    /**
     * 获取支付方式名称
     * @param paymentMethod
     * @return
     */
    String getPaymentMethodName(Integer paymentMethod);

    /**
     * 根据会员等级获取折扣(10倍)
     * @return
     */
    String getMemberDiscountByLevel(Integer level);

    /**
     * 查询康护状态
     * @param status
     * @return
     */
    String getTreatmentStatusName(Integer status);

    /**
     * 获取会员卡赠送标准
     *
     * @return
     */
    List<Dict> getLevelPresentDicts();

    /**
     * 根据预约获取项目名称
     * @param id
     * @return
     */
    List<String> getProjectNamesByAppointment(Integer id);

    /**
     * 外协中心申请项目
     * @return
     */
    List<Dict> findAllOutSourceProject();

    /**
     * 特殊申请项目名称
     * @param project
     * @return
     */
    String getProjectNameBySpecialApplication(Integer project);

    /**
     * 特殊申请状态
     * @param status
     * @return
     */
    String getSpecialApplicationStatusName(Integer status);

    /**
     * 特殊申请 - 查询会员
     * @param specialApplication
     * @return
     */
    String getMemberNameBySpecialApplication(Integer specialApplication);

    /**
     * 特殊申请 - 状态显示
     * @param specialApplication
     * @return
     */
    String getStatusBySpecialApplication(Integer specialApplication);

    /**
     * 特殊申请 - 查询单号
     * @return
     */
    String getNumberBySpecialApplication(Integer id);

    /**
     * 特殊申请结算 - 状态计算
     * @param id
     * @return
     */
    String getStatusBySpecialApplicationSettlement(Integer id);

    /**
     * 获取供货商名称
     * @param supplier
     * @return
     */
    String getMedicineSupplier(Integer supplier);

    /**
     * 外协订单状态
     * @param status
     * @return
     */
    String getOutsourceOrderStatus(Integer status);

    /**
     * 积分类型名称
     * @param type
     * @return
     */
    String getPointTypeName(Integer type);

    /**
     * 根据会员卡获取会员信息
     * @param card
     * @return
     */
    Member getMemberByCard(Integer card);

    /**
     * 所有项目分类
     * @return
     */
    List<Dict> findAllProjectCategories();

    /**
     * 非体验支付方式
     * @return
     */
    List<Dict> paymentMethodNameWithoutExp();

    /**
     * 根据结算获取产品
     * @param id
     * @return
     */
    List<Map<String, Object>> getProductNamesBySettlement(Integer id);

    /**
     * 最终效果字典
     * @return
     */
    List<Dict> findFinalEffectDict();

    /**
     * 客户满意度字典
     * @return
     */
    List<Dict> findCustomerSatisfactionDict();

    /**
     * 根据ID查找耗材名称
     * @param id
     * @return
     */
    String getConsumableName(Integer id);

    String getDepartmentNameByConsumable(Integer consumable);
}
