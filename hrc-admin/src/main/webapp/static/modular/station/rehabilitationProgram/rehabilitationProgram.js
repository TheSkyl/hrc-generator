/**
 * 健康康复管理初始化
 */
var RehabilitationProgram = {
    id: "RehabilitationProgramTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RehabilitationProgram.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '会员姓名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '康护', field: 'disease', visible: true, align: 'center', valign: 'middle'},
            {title: '方案说明', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '最终疗效', field: 'curativeEffect', visible: true, align: 'center', valign: 'middle'},
            {title: '备注说明', field: 'remark', visible: false, align: 'center', valign: 'middle'},
            {title: '制定门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '操作员', field: 'createdByName', visible: true, align: 'center', valign: 'middle'},
            {title: '制定日期', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
RehabilitationProgram.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RehabilitationProgram.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加康复方案
 */
RehabilitationProgram.openAddRehabilitationProgram = function () {
    var index = layer.open({
        type: 2,
        title: '添加康复方案',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rehabilitationProgram/rehabilitationProgram_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看康复方案详情
 */
RehabilitationProgram.openRehabilitationProgramDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '康复方案详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/rehabilitationProgram/rehabilitationProgram_update/' + RehabilitationProgram.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除康复方案
 */
RehabilitationProgram.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/rehabilitationProgram/delete", function (data) {
            Feng.success("删除成功!");
            RehabilitationProgram.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("rehabilitationProgramId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询康复方案列表
 */
RehabilitationProgram.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    RehabilitationProgram.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RehabilitationProgram.initColumn();
    var table = new BSTable(RehabilitationProgram.id, "/rehabilitationProgram/list", defaultColunms);
    table.setPaginationType("client");
    RehabilitationProgram.table = table.init();
});
