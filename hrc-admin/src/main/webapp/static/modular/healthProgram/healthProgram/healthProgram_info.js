/**
 * 初始化健康方案管理详情对话框
 */
var HealthProgramInfoDlg = {
    healthProgramInfoData : {}
};

/**
 * 清除数据
 */
HealthProgramInfoDlg.clearData = function() {
    this.healthProgramInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HealthProgramInfoDlg.set = function(key, val) {
    this.healthProgramInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HealthProgramInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HealthProgramInfoDlg.close = function() {
    parent.layer.close(window.parent.HealthProgram.layerIndex);
}

/**
 * 收集数据
 */
HealthProgramInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('symptom')
    .set('program')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
HealthProgramInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/healthProgram/add", function(data){
        Feng.success("添加成功!");
        window.parent.HealthProgram.table.refresh();
        HealthProgramInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.healthProgramInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HealthProgramInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/healthProgram/update", function(data){
        Feng.success("修改成功!");
        window.parent.HealthProgram.table.refresh();
        HealthProgramInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.healthProgramInfoData);
    ajax.start();
}

$(function() {
    var cid = $('#categoryValue').val();
    $("#category").find('option[value="'+cid+'"]').prop('selected',true);
});
