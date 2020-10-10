/**
 * 耗材管理初始化
 */
var Consumable = {
    id: "ConsumableTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Consumable.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '耗材名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '耗材编号', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '规格型号', field: 'specification', visible: true, align: 'center', valign: 'middle'},
            {title: '耗材单价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '计量单位', field: 'measureUnit', visible: true, align: 'center', valign: 'middle'},
            {title: '生产企业', field: 'manufacturer', visible: true, align: 'center', valign: 'middle'},
            {title: '所属门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '数量', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Consumable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Consumable.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加耗材
 */
Consumable.openAddConsumable = function () {
    var index = layer.open({
        type: 2,
        title: '添加耗材',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/consumable/consumable_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看耗材详情
 */
Consumable.openConsumableDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '耗材详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/consumable/consumable_update/' + Consumable.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开耗材调库界面
 */
Consumable.openConsumableTransfer = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '耗材调库',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/consumable/consumable_transfer/' + Consumable.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开耗材盘库界面
 */
Consumable.openConsumableCheck = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '耗材盘库',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/consumable/consumable_check/' + Consumable.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除耗材
 */
Consumable.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/consumable/delete", function (data) {
            Feng.success("删除成功!");
            Consumable.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("consumableId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询耗材列表
 */
Consumable.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Consumable.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Consumable.initColumn();
    var table = new BSTable(Consumable.id, "/consumable/list", defaultColunms);
    table.setPaginationType("client");
    Consumable.table = table.init();
});
