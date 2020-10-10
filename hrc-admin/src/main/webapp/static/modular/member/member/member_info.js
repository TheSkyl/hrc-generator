/**
 * 初始化会员详情对话框
 */
var MemberInfoDlg = {
    memberInfoData : {}
};

/**
 * 清除数据
 */
MemberInfoDlg.clearData = function() {
    this.memberInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberInfoDlg.set = function(key, val) {
    this.memberInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberInfoDlg.close = function() {
    parent.layer.close(window.parent.Member.layerIndex);
}

/**
 * 收集数据
 */
MemberInfoDlg.collectData = function() {
    this
    .set('id')
    .set('nickname')
    .set('phone')
    .set('realName')
    .set('gender')
    .set('wechat')
    .set('wechatQRCode')
    .set('birthday')
    .set('idCard')
    .set('avatar')
    .set('height')
    .set('weight')
    .set('bloodType')
    .set('chestCircumference')
    .set('waistCircumference')
    .set('hipCircumference')
    .set('occupation')
    .set('email')
    .set('address')
    .set('mailingAddress')
    .set('maritalStatus')
    .set('haveChildren')
    .set('childrenGender')
    .set('childrenBirthday')
    .set('familyMember')
    .set('favoriteColors')
    .set('favoriteClothingStyle')
    .set('favoriteFood')
    .set('favoriteDrinking')
    .set('favoriteMusic')
    .set('hobby')
    .set('preferredNursingTime')
    .set('preferredContactMethod')
    .set('preferredContactTime')
    .set('customerServiceMemo')
    .set('geneticDiseaseHistory')
    .set('pastDiseaseHistory')
    .set('pastTreatHistory')
    .set('pastMedicineHistory')
    .set('drugAllergyHistory')
    .set('alcoholAllergy')
    .set('existingSymptom')
    .set('clinic')
    .set('introducer');
}

/**
 * 提交添加
 */
MemberInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/member/add", function(data){
        if(data.code!=200){
            Feng.alert(data.message);
            return;
        }
            Feng.success("添加成功!");
            window.parent.Member.table.refresh();
            MemberInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/member/update", function(data){
        if(data.code!=200){
            Feng.alert(data.message);
            return;
        }
        Feng.success("修改成功!");
        window.parent.Member.table.refresh();
        MemberInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberInfoData);
    ajax.start();
}

$(function() {
    // 初始化字典
    $("#gender").val($("#genderValue").val());
    $("#bloodType").val($("#bloodTypeValue").val());
    $("#maritalStatus").val($("#maritalStatusValue").val());
    $("#haveChildren").val($("#haveChildrenValue").val());
    $("#preferredNursingTime").val($("#preferredNursingTimeValue").val());
    $("#alcoholAllergy").val($("#alcoholAllergyValue").val());

    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
    // 初始化微信二维码上传
    var wechatQRCodeUp = new $WebUpload("wechatQRCode");
    wechatQRCodeUp.setUploadBarId("progressBar");
    wechatQRCodeUp.init();

    var memberDeptBsSuggest = $("#clinicValue").bsSuggest({
        idField: 'dept',
        keyField: 'name',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {dept: "编号", name: "名称"},
        showHeader: true,
        url: "/dept/suggestList/",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"dept": json[i]['id'], "name": json[i]['name']})
            }
            return data
        }
    });
    $("input#clinicValue").on("onSetSelectValue", function (event, result) {
        $('#clinic').val(result['id']);
    });

    var memberBsSuggest = $("#introducerValue").bsSuggest({
        idField: 'member',
        keyField: 'name',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {member: "编号", name: "姓名", mobile: "手机号"},
        showHeader: true,
        url: "/member/suggestList/",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"member": json[i]['id'], "name": json[i]['name'], "mobile": json[i]['mobile']})
            }
            return data
        }
    });
    $("input#introducerValue").on("onSetSelectValue", function (event, result) {
        var mid = $('#id').val();
        if(mid && mid.length>0 && result['id'] == $('#id').val()){
            Feng.alert("不能自我推荐哦");
            $('#introducerValue').val('');
            $('#introducer').val('');
        }
        $('#introducer').val(result['id']);
    });
});
