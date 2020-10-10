/**
 * 设备管理管理初始化
 */
var Device = {
    id: "DeviceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Device.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '设备编号', field: 'deviceNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '设备名称', field: 'deviceName', visible: true, align: 'center', valign: 'middle'},
            {title: '设备单价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '设备数量', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '设备状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
            {title: '采购日期', field: 'purchaseTime', visible: true, align: 'center', valign: 'middle'},
            {title: '所属门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '维护人', field: 'maintainerName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Device.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Device.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加设备管理
 */
Device.openAddDevice = function () {
    var index = layer.open({
        type: 2,
        title: '添加设备',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/device/device_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看设备管理详情
 */
Device.openDeviceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '设备详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/device/device_update/' + Device.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除设备管理
 */
Device.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/device/delete", function (data) {
            Feng.success("删除成功!");
            Device.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("deviceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询设备管理列表
 */
Device.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Device.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Device.initColumn();
    var table = new BSTable(Device.id, "/device/list", defaultColunms);
    table.setPaginationType("client");
    Device.table = table.init();
});
