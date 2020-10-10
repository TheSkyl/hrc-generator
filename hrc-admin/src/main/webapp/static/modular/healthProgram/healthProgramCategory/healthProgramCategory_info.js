/**
 * 初始化健康方案分类详情对话框
 */
var HealthProgramCategoryInfoDlg = {
    healthProgramCategoryInfoData : {}
};

/**
 * 清除数据
 */
HealthProgramCategoryInfoDlg.clearData = function() {
    this.healthProgramCategoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HealthProgramCategoryInfoDlg.set = function(key, val) {
    this.healthProgramCategoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HealthProgramCategoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HealthProgramCategoryInfoDlg.close = function() {
    parent.layer.close(window.parent.HealthProgramCategory.layerIndex);
}

/**
 * 收集数据
 */
HealthProgramCategoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('code')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
HealthProgramCategoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/healthProgramCategory/add", function(data){
        Feng.success("添加成功!");
        window.parent.HealthProgramCategory.table.refresh();
        HealthProgramCategoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.healthProgramCategoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HealthProgramCategoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/healthProgramCategory/update", function(data){
        Feng.success("修改成功!");
        window.parent.HealthProgramCategory.table.refresh();
        HealthProgramCategoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.healthProgramCategoryInfoData);
    ajax.start();
}

$(function() {

});
