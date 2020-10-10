/**
 * 健康方案管理管理初始化
 */
var HealthProgram = {
    id: "HealthProgramTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HealthProgram.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '方案名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '症状描述', field: 'symptom', visible: true, align: 'center', valign: 'middle'},
            {title: '方案描述', field: 'program', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'flag', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HealthProgram.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HealthProgram.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加健康方案管理
 */
HealthProgram.openAddHealthProgram = function () {
    var index = layer.open({
        type: 2,
        title: '添加健康方案管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/healthProgram/healthProgram_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看健康方案管理详情
 */
HealthProgram.openHealthProgramDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '健康方案管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/healthProgram/healthProgram_update/' + HealthProgram.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除健康方案管理
 */
HealthProgram.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/healthProgram/delete", function (data) {
            Feng.success("删除成功!");
            HealthProgram.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("healthProgramId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询健康方案管理列表
 */
HealthProgram.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    HealthProgram.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = HealthProgram.initColumn();
    var table = new BSTable(HealthProgram.id, "/healthProgram/list", defaultColunms);
    table.setPaginationType("client");
    HealthProgram.table = table.init();
});
