/**
 * 初始化健康档案详情对话框
 */
var MemberHealthRecordInfoDlg = {
    memberHealthRecordInfoData : {}
};

/**
 * 清除数据
 */
MemberHealthRecordInfoDlg.clearData = function() {
    this.memberHealthRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberHealthRecordInfoDlg.set = function(key, val) {
    this.memberHealthRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberHealthRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberHealthRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberHealthRecord.layerIndex);
}

/**
 * 收集数据
 */
MemberHealthRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('member')
    .set('project')
    .set('disease')
    .set('evaluation')
    .set('doc')
    .set('remark')
    .set('department')
    .set('createdBy')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
MemberHealthRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    var att = [];
    $('.att-link').each(function (){
        att.push($(this).attr('href'));
    });
    this.set('attachment',att);

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberHealthRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberHealthRecord.table.refresh();
        MemberHealthRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberHealthRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberHealthRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberHealthRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberHealthRecord.table.refresh();
        MemberHealthRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberHealthRecordInfoData);
    ajax.start();
}

/**
 * 选择方案
 */
MemberHealthRecordInfoDlg.selectSolution = function() {
    var index = layer.open({
        type: 2,
        title: '选择健康方案',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberHealthRecord/solutions'
    });
    this.layerIndex = index;
}

/**
 * 康复报告
 */
MemberHealthRecordInfoDlg.openRehabilitationProgram = function() {
    var hrid = $("#id").val();
    var index = parent.layer.open({
        type: 2,
        title: '选择健康方案',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rehabilitationProgram/rehabilitationProgram_update/0?hrid='+ hrid
    });
    parent.layerIndex = index;
}

$(function() {
    // var hrUp = new $DocUpload("doc");
    // hrUp.setUploadBarId("progressBar");
    // hrUp.init();
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
        $.post("/member/infoById",{id:result['id']},function(result){
            $('#memmobile').text(result.phone);
        });
    });

    var hrDeptBsSuggest = $("#departmentValue").bsSuggest({
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
            $('#createdBy').empty();
            $.each(result,function(i,dt){
                $('#createdBy').append($('<option>').val(dt.value).text(dt.key));
            });
        });
    });


});
