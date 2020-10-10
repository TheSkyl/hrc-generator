/**
 * 外协订单管理初始化
 */
var OutsourceOrder = {
    id: "OutsourceOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OutsourceOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '外协单号', field: 'orderNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '供应商', field: 'supplierName', visible: true, align: 'center', valign: 'middle'},
            {title: '收货人', field: 'receiver', visible: true, align: 'center', valign: 'middle'},
            {title: '收货人电话', field: 'receiverPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '收货人地址', field: 'receiverAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '订单状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
OutsourceOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OutsourceOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加外协订单
 */
OutsourceOrder.openAddOutsourceOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加外协订单',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/outsourceOrder/outsourceOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看外协订单详情
 */
OutsourceOrder.openOutsourceOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '外协订单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/outsourceOrder/outsourceOrder_update/' + OutsourceOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看外协订单详情
 */
OutsourceOrder.openOutsourceOrderDeliver = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '外协订单详情',
            area: ['1000px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/outsourceOrder/outsourceOrder_deliver/' + OutsourceOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看外协订单详情 - 结算
 */
OutsourceOrder.openOutsourceOrderSettle = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '外协订单详情',
            area: ['1000px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/outsourceOrder/outsourceOrder_settle/' + OutsourceOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除外协订单
 */
OutsourceOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/outsourceOrder/delete", function (data) {
            Feng.success("删除成功!");
            OutsourceOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("outsourceOrderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询外协订单列表
 */
OutsourceOrder.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    OutsourceOrder.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OutsourceOrder.initColumn();
    var table = new BSTable(OutsourceOrder.id, "/outsourceOrder/list", defaultColunms);
    table.setPaginationType("client");
    OutsourceOrder.table = table.init();
});
