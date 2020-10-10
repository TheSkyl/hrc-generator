/**
 * 初始化专家管理详情对话框
 */
var ExpertInfoDlg = {
    expertInfoData : {}
};

/**
 * 清除数据
 */
ExpertInfoDlg.clearData = function() {
    this.expertInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpertInfoDlg.set = function(key, val) {
    this.expertInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpertInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ExpertInfoDlg.close = function() {
    parent.layer.close(window.parent.Expert.layerIndex);
}

/**
 * 收集数据
 */
ExpertInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('birthday')
    .set('address')
    .set('phone')
    .set('specialty')
    .set('profile');
}

/**
 * 提交添加
 */
ExpertInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/expert/add", function(data){
        Feng.success("添加成功!");
        window.parent.Expert.table.refresh();
        ExpertInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.expertInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ExpertInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/expert/update", function(data){
        Feng.success("修改成功!");
        window.parent.Expert.table.refresh();
        ExpertInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.expertInfoData);
    ajax.start();
}

$(function() {

});
