/**
 * 初始化会员卡详情对话框
 */
var MembershipCardInfoDlg = {
    membershipCardInfoData : {}
};

/**
 * 清除数据
 */
MembershipCardInfoDlg.clearData = function() {
    this.membershipCardInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MembershipCardInfoDlg.set = function(key, val) {
    this.membershipCardInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MembershipCardInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MembershipCardInfoDlg.close = function() {
    parent.layer.close(window.parent.MembershipCard.layerIndex);
}

/**
 * 收集数据
 */
MembershipCardInfoDlg.collectData = function() {
    this
    .set('id')
    .set('number')
    .set('balance')
    .set('user')
    .set('level')
    .set('project')
    .set('dueDate')
    .set('createTime');
}

/**
 * 提交添加
 */
MembershipCardInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/membershipCard/add", function(data){
        Feng.success("添加成功!");
        window.parent.MembershipCard.table.refresh();
        MembershipCardInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.membershipCardInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MembershipCardInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/membershipCard/update", function(data){
        Feng.success("修改成功!");
        window.parent.MembershipCard.table.refresh();
        MembershipCardInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.membershipCardInfoData);
    ajax.start();
}

$(function() {
    var memberBsSuggest = $("#userValue").bsSuggest({
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
    $("input#userValue").on("onSetSelectValue", function (event, result) {
        $('#user').val(result['id']);
    });
    $("#project").val($('#projectValue').val());
    $('#project').attr("disabled",true);
});
