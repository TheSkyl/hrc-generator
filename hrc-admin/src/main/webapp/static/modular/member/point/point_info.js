/**
 * 初始化积分详情对话框
 */
var PointInfoDlg = {
    pointInfoData : {}
};

/**
 * 清除数据
 */
PointInfoDlg.clearData = function() {
    this.pointInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PointInfoDlg.set = function(key, val) {
    this.pointInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PointInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PointInfoDlg.close = function() {
    parent.layer.close(window.parent.Point.layerIndex);
}

/**
 * 收集数据
 */
PointInfoDlg.collectData = function() {
    this
    .set('id')
    .set('member')
    .set('type')
    .set('number');
}

// /**
//  * 提交添加
//  */
// PointInfoDlg.addSubmit = function() {
//
//     this.clearData();
//     this.collectData();
//
//     //提交信息
//     var ajax = new $ax(Feng.ctxPath + "/point/add", function(data){
//         Feng.success("添加成功!");
//         window.parent.Point.table.refresh();
//         PointInfoDlg.close();
//     },function(data){
//         Feng.error("添加失败!" + data.responseJSON.message + "!");
//     });
//     ajax.set(this.pointInfoData);
//     ajax.start();
// }
//
// /**
//  * 提交修改
//  */
// PointInfoDlg.editSubmit = function() {
//
//     this.clearData();
//     this.collectData();
//
//     //提交信息
//     var ajax = new $ax(Feng.ctxPath + "/point/update", function(data){
//         Feng.success("修改成功!");
//         window.parent.Point.table.refresh();
//         PointInfoDlg.close();
//     },function(data){
//         Feng.error("修改失败!" + data.responseJSON.message + "!");
//     });
//     ajax.set(this.pointInfoData);
//     ajax.start();
// }

$(function() {

});
