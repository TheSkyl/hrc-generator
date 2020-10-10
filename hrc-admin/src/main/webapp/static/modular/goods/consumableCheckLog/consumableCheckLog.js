/**
 * 库存盘库管理初始化
 */
var ConsumableCheckLog = {
    id: "ConsumableCheckLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ConsumableCheckLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '耗材', field: 'consumableName', visible: true, align: 'center', valign: 'middle'},
            {title: '所属门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '增建数量', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '盘库备注', field: 'memo', visible: true, align: 'center', valign: 'middle'},
            {title: '盘库时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'createdByName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ConsumableCheckLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ConsumableCheckLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加库存盘库
 */
ConsumableCheckLog.openAddConsumableCheckLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加库存盘库',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/consumableCheckLog/consumableCheckLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看库存盘库详情
 */
ConsumableCheckLog.openConsumableCheckLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '库存盘库详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/consumableCheckLog/consumableCheckLog_update/' + ConsumableCheckLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除库存盘库
 */
ConsumableCheckLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/consumableCheckLog/delete", function (data) {
            Feng.success("删除成功!");
            ConsumableCheckLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("consumableCheckLogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询库存盘库列表
 */
ConsumableCheckLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ConsumableCheckLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ConsumableCheckLog.initColumn();
    var table = new BSTable(ConsumableCheckLog.id, "/consumableCheckLog/list", defaultColunms);
    table.setPaginationType("client");
    ConsumableCheckLog.table = table.init();
});
