/**
 * 初始化外协订单详情对话框
 */
var OutsourceOrderInfoDlg = {
    outsourceOrderInfoData : {},
    outsourceOrderDeliveryInfoData : {},
    outsourceOrderSettleInfoData : {}
};

/**
 * 清除数据
 */
OutsourceOrderInfoDlg.clearData = function() {
    this.outsourceOrderInfoData = {};
}

/**
 * 清除数据
 */
OutsourceOrderInfoDlg.clearDeliveryData = function() {
    this.outsourceOrderDeliveryInfoData = {};
}

/**
 * 清除数据
 */
OutsourceOrderInfoDlg.clearSettleData = function() {
    this.outsourceOrderSettleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutsourceOrderInfoDlg.set = function(key, val) {
    this.outsourceOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutsourceOrderInfoDlg.setd = function(key, val) {
    this.outsourceOrderDeliveryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutsourceOrderInfoDlg.sets = function(key, val) {
    this.outsourceOrderSettleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutsourceOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OutsourceOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.OutsourceOrder.layerIndex);
}

/**
 * 收集数据
 */
OutsourceOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('supplier')
    .set('receiver')
    .set('receiverPhone')
    .set('receiverAddress')
    .set('purchaseMemo')
    .set('remark')
    .set('status')
    .set('createdTime')
    .set('createdBy')
    .set('flag');
}

/**
 * 收集数据
 */
OutsourceOrderInfoDlg.collectDeliveryData = function() {
    this
    .setd('id')
    .setd('outsourceOrder')
    .setd('shippingInstructions')
    .setd('medicinePrice')
    .setd('deliveryTime')
    .setd('shippingInformation')
    .setd('shippingOrderNumber')
    .setd('shippingPrice');
}

/**
 * 收集数据
 */
OutsourceOrderInfoDlg.collectSettleData = function() {
    this
    .sets('outsourceOrder')
    .sets('amount')
    .sets('payType')
    .sets('payTime')
    .sets('accountName')
    .sets('accountNumber')
    .sets('accountBank')
    .sets('remark');
}

/**
 * 提交添加
 */
OutsourceOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/outsourceOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.OutsourceOrder.table.refresh();
        OutsourceOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outsourceOrderInfoData);
    ajax.start();
}

/**
 * 提交添加
 */
OutsourceOrderInfoDlg.deliverSubmit = function() {

    this.clearDeliveryData();
    this.collectDeliveryData();

    //提交信息
        var ajax = new $ax(Feng.ctxPath + "/outsourceOrder/deliver", function(data){
        Feng.success("发货成功!");
        window.parent.OutsourceOrder.table.refresh();
        OutsourceOrderInfoDlg.close();
    },function(data){
        Feng.error("发货失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outsourceOrderDeliveryInfoData);
    ajax.start();
}

/**
 * 提交添加
 */
OutsourceOrderInfoDlg.settleSubmit = function() {

    this.clearSettleData();
    this.collectSettleData();

    //提交信息
        var ajax = new $ax(Feng.ctxPath + "/outsourceOrder/settle", function(data){
        Feng.success("结算成功!");
        window.parent.OutsourceOrder.table.refresh();
        OutsourceOrderInfoDlg.close();
    },function(data){
        Feng.error("结算失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outsourceOrderSettleInfoData);
    ajax.start();
}

/**
 * 提交添加
 */
OutsourceOrderInfoDlg.rushOrder = function() {
    var osId = $('#id').val();
    this.set("id")

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/outsourceOrder/rushOrder", function(data){
        Feng.success("抢单成功!");
        window.parent.OutsourceOrder.table.refresh();
        OutsourceOrderInfoDlg.close();
        OutsourceOrderInfoDlg.openOutsourceOrderDeliver(osId);
    },function(data){
        Feng.error("抢单失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outsourceOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OutsourceOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/outsourceOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.OutsourceOrder.table.refresh();
        OutsourceOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outsourceOrderInfoData);
    ajax.start();
}



/**
 * 打开查看外协订单详情
 */
OutsourceOrderInfoDlg.openOutsourceOrderDeliver = function (osId) {
    if (osId) {
        var index = parent.layer.open({
            type: 2,
            title: '外协订单详情',
            area: ['1000px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/outsourceOrder/outsourceOrder_deliver/' + osId
        });
        parent.layerIndex = index;
    }
};

$(function() {

});
