/**
 * 初始化特殊申请详情对话框
 */
var SpecialApplicationInfoDlg = {
    specialApplicationInfoData : {}
};

/**
 * 清除数据
 */
SpecialApplicationInfoDlg.clearData = function() {
    this.specialApplicationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialApplicationInfoDlg.set = function(key, val) {
    this.specialApplicationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialApplicationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SpecialApplicationInfoDlg.close = function() {
    parent.layer.close(window.parent.SpecialApplication.layerIndex);
}

/**
 * 收集数据
 */
SpecialApplicationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('member')
    .set('project')
    .set('conditionDescription')
    .set('diseaseHistory')
    .set('recentCheck')
    .set('recentTreatment')
    .set('remark')
    .set('department')
    .set('createTime')
    .set('flag')
    .set('createdBy')
    .set('status')
    .set('applicationNumber');
}

/**
 * 提交添加
 */
SpecialApplicationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    var att = [];
    $('.att-link').each(function (){
        att.push($(this).attr('href'));
    });
    this.set('attachment',att);

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/specialApplication/add", function(data){
        Feng.success("添加成功!");
        window.parent.SpecialApplication.table.refresh();
        SpecialApplicationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialApplicationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SpecialApplicationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/specialApplication/update", function(data){
        Feng.success("修改成功!");
        window.parent.SpecialApplication.table.refresh();
        SpecialApplicationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialApplicationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SpecialApplicationInfoDlg.approve = function(status) {

    this.clearData();
    this.collectData();
    this.set("status",status);
    var wds = this;

    //提交信息
    Feng.confirm("确认审核此申请吗?", function () {

        var ajax = new $ax(Feng.ctxPath + "/specialApplication/update", function(data){
            Feng.success("修改成功!");
            window.parent.SpecialApplication.table.refresh();
            SpecialApplicationInfoDlg.close();
        },function(data){
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(wds.specialApplicationInfoData);
        ajax.start();
    });

}

$(function() {
    var hrUp = new $MultiDocUpload("doc");
    hrUp.setUploadBarId("progressBar");
    hrUp.init();
    var memberBsSuggest = $("#memberValue").bsSuggest({
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
    $("input#memberValue").on("onSetSelectValue", function (event, result) {
        $('#member').val(result['id']);
    });

    $("#project").val($('#projectValue').val());
});
