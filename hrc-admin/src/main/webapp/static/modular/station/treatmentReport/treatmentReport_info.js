/**
 * 初始化康护报告详情对话框
 */
var TreatmentReportInfoDlg = {
    treatmentReportInfoData : {}
};

/**
 * 清除数据
 */
TreatmentReportInfoDlg.clearData = function() {
    this.treatmentReportInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TreatmentReportInfoDlg.set = function(key, val) {
    this.treatmentReportInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TreatmentReportInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TreatmentReportInfoDlg.close = function() {
    parent.layer.close(window.parent.TreatmentReport.layerIndex);
}

/**
 * 收集数据
 */
TreatmentReportInfoDlg.collectData = function() {
    this
    .set('id')
    .set('member')
    .set('project')
    .set('summary')
    .set('product')
    .set('consumable')
    .set('amount')
    .set('createTime')
    .set('department')
    .set('createdBy')
    .set('flag');
}

/**
 * 提交添加
 */
TreatmentReportInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/treatmentReport/add", function(data){
        Feng.success("添加成功!");
        window.parent.TreatmentReport.table.refresh();
        TreatmentReportInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.treatmentReportInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TreatmentReportInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/treatmentReport/update", function(data){
        Feng.success("修改成功!");
        window.parent.TreatmentReport.table.refresh();
        TreatmentReportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.treatmentReportInfoData);
    ajax.start();
}

$(function() {

    var treatmentUserBsSuggest = $("#memberValue").bsSuggest({
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

    var treatmentDeptBsSuggest = $("#departmentValue").bsSuggest({
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
    $("input#departmentValue").on("onSetSelectValue", function (event, result) {
        $('#department').val(result['id']);
        $.post("/mgr/userByDepartment",{dept:result['id']},function(result){
            $('#createdBy   ').empty();
            $.each(result,function(i,dt){
                $('#createdBy').append($('<option>').val(dt.value).text(dt.key));
            });
        });
    });
});
