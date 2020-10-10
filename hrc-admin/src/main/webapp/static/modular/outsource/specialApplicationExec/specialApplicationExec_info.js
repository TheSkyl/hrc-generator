/**
 * 初始化申请执行详情对话框
 */
var SpecialApplicationExecInfoDlg = {
    specialApplicationExecInfoData : {}
};

/**
 * 清除数据
 */
SpecialApplicationExecInfoDlg.clearData = function() {
    this.specialApplicationExecInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialApplicationExecInfoDlg.set = function(key, val) {
    this.specialApplicationExecInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialApplicationExecInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SpecialApplicationExecInfoDlg.close = function() {
    parent.layer.close(window.parent.SpecialApplicationExec.layerIndex);
}

/**
 * 收集数据
 */
SpecialApplicationExecInfoDlg.collectData = function() {
    this
    .set('id')
    .set('specialApplication')
    .set('cost')
    .set('treatmentProcess')
    .set('instructions')
    .set('contact')
    .set('phone')
    .set('status')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
SpecialApplicationExecInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/specialApplicationExec/add", function(data){
        Feng.success("添加成功!");
        window.parent.SpecialApplicationExec.table.refresh();
        SpecialApplicationExecInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialApplicationExecInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SpecialApplicationExecInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    this.set('experts');

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/specialApplicationExec/update", function(data){
        Feng.success("修改成功!");
        window.parent.SpecialApplicationExec.table.refresh();
        SpecialApplicationExecInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialApplicationExecInfoData);
    ajax.start();
}

$(function() {
    $('#experts').attr('multiple','').attr('data-placeholder','请选择专家列表').addClass('chosen-select').val('');
    var config = {
        ".chosen-select": {},
        ".chosen-select-deselect": {allow_single_deselect: !0},
        ".chosen-select-no-single": {disable_search_threshold: 10},
        ".chosen-select-no-results": {no_results_text: "暂无记录"},
        ".chosen-select-width": {width: "95%"}
    };
    var $selp = $('#selp');
    if($selp.length>0 && $selp.val().length>0){
        $.each($selp.val().split(','), function(key, value) {
            $("#experts option[value='" + value + "']").prop("selected", true);
        });
    }
    for (var selector in config) $(selector).chosen(config[selector]).trigger("chosen:updated");
});
