/**
 * 耗材调库记录管理初始化
 */
var ConsumableTransferLog = {
    id: "ConsumableTransferLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ConsumableTransferLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '出库耗材', field: 'srcConsumableName', visible: true, align: 'center', valign: 'middle'},
            {title: '入库耗材', field: 'destConsumableName', visible: true, align: 'center', valign: 'middle'},
            {title: '调拨数量', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '出库门店', field: 'srcDepartmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '入库门店', field: 'destDepartmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'createdByName', visible: true, align: 'center', valign: 'middle'},
            {title: '调库时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ConsumableTransferLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ConsumableTransferLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加耗材调库记录
 */
ConsumableTransferLog.openAddConsumableTransferLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加耗材调库记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/consumableTransferLog/consumableTransferLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看耗材调库记录详情
 */
ConsumableTransferLog.openConsumableTransferLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '耗材调库记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/consumableTransferLog/consumableTransferLog_update/' + ConsumableTransferLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除耗材调库记录
 */
ConsumableTransferLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/consumableTransferLog/delete", function (data) {
            Feng.success("删除成功!");
            ConsumableTransferLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("consumableTransferLogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询耗材调库记录列表
 */
ConsumableTransferLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ConsumableTransferLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ConsumableTransferLog.initColumn();
    var table = new BSTable(ConsumableTransferLog.id, "/consumableTransferLog/list", defaultColunms);
    table.setPaginationType("client");
    ConsumableTransferLog.table = table.init();
});
