/**
 * 初始化库存盘库详情对话框
 */
var ConsumableCheckLogInfoDlg = {
    consumableCheckLogInfoData : {}
};

/**
 * 清除数据
 */
ConsumableCheckLogInfoDlg.clearData = function() {
    this.consumableCheckLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumableCheckLogInfoDlg.set = function(key, val) {
    this.consumableCheckLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumableCheckLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ConsumableCheckLogInfoDlg.close = function() {
    parent.layer.close(window.parent.Consumable.layerIndex);
}

/**
 * 收集数据
 */
ConsumableCheckLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('consumable')
    .set('amount')
    .set('memo')
    .set('createTime')
    .set('createdBy')
    .set('flag');
}

/**
 * 提交添加
 */
ConsumableCheckLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumableCheckLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.Consumable.table.refresh();
        ConsumableCheckLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumableCheckLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ConsumableCheckLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumableCheckLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.ConsumableCheckLog.table.refresh();
        ConsumableCheckLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumableCheckLogInfoData);
    ajax.start();
}

$(function() {

});
