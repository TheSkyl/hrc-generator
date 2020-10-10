package com.cebrains.hrc.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author frank123
 * @since 2018-03-08
 */
@TableName("member")
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 微信二维码
     */
    @TableField("wechat_qr_code")
    private String wechatQRCode;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 性别,1男2女
     */
    private Integer gender;
    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 身高(cm)
     */
    private Integer height;
    /**
     * 体重(kg)
     */
    private Integer weight;
    /**
     * 血型(字典)
     */
    @TableField("blood_type")
    private Integer bloodType;
    /**
     * 胸围(cm)
     */
    @TableField("chest_circumference")
    private Integer chestCircumference;
    /**
     * 腰围(cm)
     */
    @TableField("waist_circumference")
    private Integer waistCircumference;
    /**
     * 臀围(cm)
     */
    @TableField("hip_circumference")
    private Integer hipCircumference;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 住址
     */
    private String address;
    /**
     * 通讯地址
     */
    @TableField("mailing_address")
    private String mailingAddress;
    /**
     * 婚姻状况
     */
    @TableField("marital_status")
    private Integer maritalStatus;
    /**
     * 是否有孩子(是否字典)
     */
    @TableField("have_children")
    private Integer haveChildren;
    /**
     * 孩子性别
     */
    @TableField("children_gender")
    private String childrenGender;
    /**
     * 孩子生日
     */
    @TableField("children_birthday")
    private String childrenBirthday;
    /**
     * 家庭成员
     */
    @TableField("family_member")
    private String familyMember;
    /**
     * 喜欢颜色
     */
    @TableField("favorite_colors")
    private String favoriteColors;
    /**
     * 喜欢的服装款式
     */
    @TableField("favorite_clothing_style")
    private String favoriteClothingStyle;
    /**
     * 喜欢水果
     */
    @TableField("favorite_food")
    private String favoriteFood;
    /**
     * 喜欢饮品
     */
    @TableField("favorite_drinking")
    private String favoriteDrinking;
    /**
     * 喜欢音乐
     */
    @TableField("favorite_music")
    private String favoriteMusic;
    /**
     * 其他爱好
     */
    private String hobby;
    /**
     * 喜欢的护理时间(字典)
     */
    @TableField("preferred_nursing_time")
    private Integer preferredNursingTime;
    /**
     * 最佳联系方式
     */
    @TableField("preferred_contact_method")
    private String preferredContactMethod;
    /**
     * 最佳联系时间
     */
    @TableField("preferred_contact_time")
    private String preferredContactTime;
    /**
     * 客户服务备注
     */
    @TableField("customer_service_memo")
    private String customerServiceMemo;
    /**
     * 遗传病史
     */
    @TableField("genetic_disease_history")
    private String geneticDiseaseHistory;
    /**
     * 过往病史
     */
    @TableField("past_disease_history")
    private String pastDiseaseHistory;
    /**
     * 过往康护史
     */
    @TableField("past_treat_history")
    private String pastTreatHistory;
    /**
     * 过往药物史
     */
    @TableField("past_medicine_history")
    private String pastMedicineHistory;
    /**
     * 药物过敏史
     */
    @TableField("drug_allergy_history")
    private String drugAllergyHistory;
    /**
     * 酒精过敏(是否字典)
     */
    @TableField("alcohol_allergy")
    private Integer alcoholAllergy;
    /**
     * 现有症状
     */
    @TableField("existing_symptom")
    private String existingSymptom;
    /**
     * 所属门店
     */
    private Integer clinic;
    /**
     * 介绍人
     */
    private Integer introducer;
    /**
     * 创建人
     */
    @TableField("created_by")
    private Integer createdBy;
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
     * 非存储字段
     */
    @TableField(exist = false)
    private String genderName;
    @TableField(exist = false)
    private String bloodTypeName;
    @TableField(exist = false)
    private String maritalStatusName;
    @TableField(exist = false)
    private String haveChildrenName;
    @TableField(exist = false)
    private String preferredNursingTimeName;
    @TableField(exist = false)
    private String alcoholAllergyName;
    @TableField(exist = false)
    private String clinicName;
    @TableField(exist = false)
    private String creatorName;
    @TableField(exist = false)
    private String introducerName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechatQRCode() {
        return wechatQRCode;
    }

    public void setWechatQRCode(String wechatQRCode) {
        this.wechatQRCode = wechatQRCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBloodType() {
        return bloodType;
    }

    public void setBloodType(Integer bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getChestCircumference() {
        return chestCircumference;
    }

    public void setChestCircumference(Integer chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    public Integer getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(Integer waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public Integer getHipCircumference() {
        return hipCircumference;
    }

    public void setHipCircumference(Integer hipCircumference) {
        this.hipCircumference = hipCircumference;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(Integer haveChildren) {
        this.haveChildren = haveChildren;
    }

    public String getChildrenGender() {
        return childrenGender;
    }

    public void setChildrenGender(String childrenGender) {
        this.childrenGender = childrenGender;
    }

    public String getChildrenBirthday() {
        return childrenBirthday;
    }

    public void setChildrenBirthday(String childrenBirthday) {
        this.childrenBirthday = childrenBirthday;
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getFavoriteColors() {
        return favoriteColors;
    }

    public void setFavoriteColors(String favoriteColors) {
        this.favoriteColors = favoriteColors;
    }

    public String getFavoriteClothingStyle() {
        return favoriteClothingStyle;
    }

    public void setFavoriteClothingStyle(String favoriteClothingStyle) {
        this.favoriteClothingStyle = favoriteClothingStyle;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public String getFavoriteDrinking() {
        return favoriteDrinking;
    }

    public void setFavoriteDrinking(String favoriteDrinking) {
        this.favoriteDrinking = favoriteDrinking;
    }

    public String getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(String favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Integer getPreferredNursingTime() {
        return preferredNursingTime;
    }

    public void setPreferredNursingTime(Integer preferredNursingTime) {
        this.preferredNursingTime = preferredNursingTime;
    }

    public String getPreferredContactMethod() {
        return preferredContactMethod;
    }

    public void setPreferredContactMethod(String preferredContactMethod) {
        this.preferredContactMethod = preferredContactMethod;
    }

    public String getPreferredContactTime() {
        return preferredContactTime;
    }

    public void setPreferredContactTime(String preferredContactTime) {
        this.preferredContactTime = preferredContactTime;
    }

    public String getCustomerServiceMemo() {
        return customerServiceMemo;
    }

    public void setCustomerServiceMemo(String customerServiceMemo) {
        this.customerServiceMemo = customerServiceMemo;
    }

    public String getGeneticDiseaseHistory() {
        return geneticDiseaseHistory;
    }

    public void setGeneticDiseaseHistory(String geneticDiseaseHistory) {
        this.geneticDiseaseHistory = geneticDiseaseHistory;
    }

    public String getPastDiseaseHistory() {
        return pastDiseaseHistory;
    }

    public void setPastDiseaseHistory(String pastDiseaseHistory) {
        this.pastDiseaseHistory = pastDiseaseHistory;
    }

    public String getPastTreatHistory() {
        return pastTreatHistory;
    }

    public void setPastTreatHistory(String pastTreatHistory) {
        this.pastTreatHistory = pastTreatHistory;
    }

    public String getPastMedicineHistory() {
        return pastMedicineHistory;
    }

    public void setPastMedicineHistory(String pastMedicineHistory) {
        this.pastMedicineHistory = pastMedicineHistory;
    }

    public String getDrugAllergyHistory() {
        return drugAllergyHistory;
    }

    public void setDrugAllergyHistory(String drugAllergyHistory) {
        this.drugAllergyHistory = drugAllergyHistory;
    }

    public Integer getAlcoholAllergy() {
        return alcoholAllergy;
    }

    public void setAlcoholAllergy(Integer alcoholAllergy) {
        this.alcoholAllergy = alcoholAllergy;
    }

    public String getExistingSymptom() {
        return existingSymptom;
    }

    public void setExistingSymptom(String existingSymptom) {
        this.existingSymptom = existingSymptom;
    }

    public Integer getClinic() {
        return clinic;
    }

    public void setClinic(Integer clinic) {
        this.clinic = clinic;
    }

    public Integer getIntroducer() {
        return introducer;
    }

    public void setIntroducer(Integer introducer) {
        this.introducer = introducer;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
        return "Member{" +
        "id=" + id +
        ", nickname=" + nickname +
        ", phone=" + phone +
        ", realName=" + realName +
        ", gender=" + gender +
        ", birthday=" + birthday +
        ", idCard=" + idCard +
        ", avatar=" + avatar +
        ", height=" + height +
        ", weight=" + weight +
        ", bloodType=" + bloodType +
        ", chestCircumference=" + chestCircumference +
        ", waistCircumference=" + waistCircumference +
        ", hipCircumference=" + hipCircumference +
        ", occupation=" + occupation +
        ", email=" + email +
        ", address=" + address +
        ", mailingAddress=" + mailingAddress +
        ", maritalStatus=" + maritalStatus +
        ", haveChildren=" + haveChildren +
        ", childrenGender=" + childrenGender +
        ", childrenBirthday=" + childrenBirthday +
        ", familyMember=" + familyMember +
        ", favoriteColors=" + favoriteColors +
        ", favoriteClothingStyle=" + favoriteClothingStyle +
        ", favoriteFood=" + favoriteFood +
        ", favoriteDrinking=" + favoriteDrinking +
        ", favoriteMusic=" + favoriteMusic +
        ", hobby=" + hobby +
        ", preferredNursingTime=" + preferredNursingTime +
        ", preferredContactMethod=" + preferredContactMethod +
        ", preferredContactTime=" + preferredContactTime +
        ", customerServiceMemo=" + customerServiceMemo +
        ", geneticDiseaseHistory=" + geneticDiseaseHistory +
        ", pastDiseaseHistory=" + pastDiseaseHistory +
        ", pastTreatHistory=" + pastTreatHistory +
        ", pastMedicineHistory=" + pastMedicineHistory +
        ", drugAllergyHistory=" + drugAllergyHistory +
        ", alcoholAllergy=" + alcoholAllergy +
        ", existingSymptom=" + existingSymptom +
        ", clinic=" + clinic +
        ", introducer=" + introducer +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setBloodTypeName(String bloodTypeName) {
        this.bloodTypeName = bloodTypeName;
    }

    public String getBloodTypeName() {
        return bloodTypeName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setHaveChildrenName(String haveChildrenName) {
        this.haveChildrenName = haveChildrenName;
    }

    public String getHaveChildrenName() {
        return haveChildrenName;
    }

    public void setPreferredNursingTimeName(String preferredNursingTimeName) {
        this.preferredNursingTimeName = preferredNursingTimeName;
    }

    public String getPreferredNursingTimeName() {
        return preferredNursingTimeName;
    }

    public void setAlcoholAllergyName(String alcoholAllergyName) {
        this.alcoholAllergyName = alcoholAllergyName;
    }

    public String getAlcoholAllergyName() {
        return alcoholAllergyName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setIntroducerName(String introducerName) {
        this.introducerName = introducerName;
    }

    public String getIntroducerName() {
        return introducerName;
    }
}
