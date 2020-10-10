/**
 * 会员预约管理初始化
 */
var Appointment = {
    id: "AppointmentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Appointment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '会员姓名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '康护项目', field: 'projectName', visible: true, align: 'center', valign: 'middle'},
            {title: '预约类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '预约门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '预约技师', field: 'technicianName', visible: true, align: 'center', valign: 'middle'},
            {title: '预约状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
Appointment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Appointment.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员预约
 */
Appointment.openAddAppointment = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员预约',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/appointment/appointment_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员预约详情
 */
Appointment.openAppointmentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员预约详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/appointment/appointment_update/' + Appointment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员预约
 */
Appointment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/appointment/delete", function (data) {
            Feng.success("删除成功!");
            Appointment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appointmentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员预约列表
 */
Appointment.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Appointment.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Appointment.initColumn();
    var table = new BSTable(Appointment.id, "/appointment/list", defaultColunms);
    table.setPaginationType("client");
    Appointment.table = table.init();
});
