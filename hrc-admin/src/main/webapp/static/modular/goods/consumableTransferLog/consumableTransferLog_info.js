/**
 * 初始化耗材调库记录详情对话框
 */
var ConsumableTransferLogInfoDlg = {
    consumableTransferLogInfoData : {}
};

/**
 * 清除数据
 */
ConsumableTransferLogInfoDlg.clearData = function() {
    this.consumableTransferLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumableTransferLogInfoDlg.set = function(key, val) {
    this.consumableTransferLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumableTransferLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ConsumableTransferLogInfoDlg.close = function() {
    parent.layer.close(window.parent.ConsumableTransferLog.layerIndex);
}

/**
 * 收集数据
 */
ConsumableTransferLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('srcId')
    .set('destId')
    .set('amount')
    .set('srcDepartment')
    .set('destDepartment')
    .set('createdBy')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
ConsumableTransferLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumableTransferLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.ConsumableTransferLog.table.refresh();
        ConsumableTransferLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumableTransferLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ConsumableTransferLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumableTransferLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.ConsumableTransferLog.table.refresh();
        ConsumableTransferLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumableTransferLogInfoData);
    ajax.start();
}

$(function() {

});
