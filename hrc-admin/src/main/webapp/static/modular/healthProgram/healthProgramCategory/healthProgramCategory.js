/**
 * 健康方案分类管理初始化
 */
var HealthProgramCategory = {
    id: "HealthProgramCategoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HealthProgramCategory.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '类别名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '类别编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HealthProgramCategory.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HealthProgramCategory.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加健康方案分类
 */
HealthProgramCategory.openAddHealthProgramCategory = function () {
    var index = layer.open({
        type: 2,
        title: '添加健康方案分类',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/healthProgramCategory/healthProgramCategory_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看健康方案分类详情
 */
HealthProgramCategory.openHealthProgramCategoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '健康方案分类详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/healthProgramCategory/healthProgramCategory_update/' + HealthProgramCategory.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除健康方案分类
 */
HealthProgramCategory.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/healthProgramCategory/delete", function (data) {
            Feng.success("删除成功!");
            HealthProgramCategory.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("healthProgramCategoryId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询健康方案分类列表
 */
HealthProgramCategory.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    HealthProgramCategory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = HealthProgramCategory.initColumn();
    var table = new BSTable(HealthProgramCategory.id, "/healthProgramCategory/list", defaultColunms);
    table.setPaginationType("client");
    HealthProgramCategory.table = table.init();
});
