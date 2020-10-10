/**
 * 康护记录管理初始化
 */
var Treatment = {
    id: "TreatmentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Treatment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '康护用户', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '康护门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '康护技师', field: 'technicianName', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Treatment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Treatment.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加康护记录
 */
Treatment.openAddTreatment = function () {
    var index = layer.open({
        type: 2,
        title: '添加康护记录',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/treatment/treatment_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看康护记录详情
 */
Treatment.openTreatmentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '康护记录详情',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/treatment/treatment_update/' + Treatment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除康护记录
 */
Treatment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/treatment/delete", function (data) {
            Feng.success("删除成功!");
            Treatment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("treatmentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 点击添加会员结算
 */
Treatment.openAddMemberSettlement = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '康护结算',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberSettlement/memberSettlement_add?tid=' + Treatment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 查询康护记录列表
 */
Treatment.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Treatment.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Treatment.initColumn();
    var url = "/treatment/list";
    var ttp = $("#ttp").val();
    if(ttp=='2'){
        url += "Record";
    }
    var table = new BSTable(Treatment.id, url, defaultColunms);
    table.setPaginationType("client");
    Treatment.table = table.init();
});
