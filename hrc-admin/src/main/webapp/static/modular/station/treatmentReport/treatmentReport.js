/**
 * 康护报告管理初始化
 */
var TreatmentReport = {
    id: "TreatmentReportTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TreatmentReport.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '会员姓名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '项目', field: 'project', visible: true, align: 'center', valign: 'middle'},
            {title: '购买产品', field: 'product', visible: true, align: 'center', valign: 'middle'},
            {title: '耗材', field: 'consumable', visible: true, align: 'center', valign: 'middle'},
            {title: '消费金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '所属门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '操作员', field: 'createdByName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TreatmentReport.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TreatmentReport.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加康护报告
 */
TreatmentReport.openAddTreatmentReport = function () {
    var index = layer.open({
        type: 2,
        title: '添加康护报告',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/treatmentReport/treatmentReport_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看康护报告详情
 */
TreatmentReport.openTreatmentReportDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '康护报告详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/treatmentReport/treatmentReport_update/' + TreatmentReport.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除康护报告
 */
TreatmentReport.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/treatmentReport/delete", function (data) {
            Feng.success("删除成功!");
            TreatmentReport.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("treatmentReportId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询康护报告列表
 */
TreatmentReport.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TreatmentReport.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TreatmentReport.initColumn();
    var table = new BSTable(TreatmentReport.id, "/treatmentReport/list", defaultColunms);
    table.setPaginationType("client");
    TreatmentReport.table = table.init();
});
