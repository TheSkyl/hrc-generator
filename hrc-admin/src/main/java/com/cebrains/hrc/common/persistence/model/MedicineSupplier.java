package com.cebrains.hrc.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 产品供应商表
 * </p>
 *
 * @author frank123
 * @since 2018-03-07
 */
@TableName("medicine_supplier")
public class MedicineSupplier extends Model<MedicineSupplier> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 供应商名称
     */
    private String name;
    /**
     * 类别
     */
    private Integer category;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String phone;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系人手机号
     */
    @TableField("contact_phone")
    private String contactPhone;
    /**
     * 法人
     */
    @TableField("legal_person")
    private String legalPerson;
    /**
     * 公司执照
     */
    @TableField("business_license")
    private String businessLicense;
    /**
     * 开户行
     */
    private String bank;
    /**
     * 开户行卡号
     */
    @TableField("account_number")
    private String accountNumber;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 标记
     */
    private Integer flag;

    /**
     * 显示字段
     * @return
     */
    @TableField(exist = false)
    private String categoryName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MedicineSupplier{" +
        "id=" + id +
        ", name=" + name +
        ", category=" + category +
        ", address=" + address +
        ", phone=" + phone +
        ", contact=" + contact +
        ", contactPhone=" + contactPhone +
        ", legalPerson=" + legalPerson +
        ", businessLicense=" + businessLicense +
        ", bank=" + bank +
        ", accountNumber=" + accountNumber +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
