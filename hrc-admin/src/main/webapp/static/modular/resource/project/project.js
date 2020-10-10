/**
 * 项目管理管理初始化
 */
var Project = {
    id: "ProjectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Project.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '项目名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '项目类别', field: 'projectCategory', visible: true, align: 'center', valign: 'middle'},
            {title: '疗程次数', field: 'treatmentCourse', visible: true, align: 'center', valign: 'middle'},
            {title: '一线疗程价格', field: 'priceTreatment1', visible: true, align: 'center', valign: 'middle'},
            {title: '二线疗程价格', field: 'priceTreatment2', visible: true, align: 'center', valign: 'middle'},
            {title: '三线疗程价格', field: 'priceTreatment3', visible: true, align: 'center', valign: 'middle'},
            {title: '一线单次价格', field: 'priceOnce1', visible: true, align: 'center', valign: 'middle'},
            {title: '二线单次价格', field: 'priceOnce2', visible: true, align: 'center', valign: 'middle'},
            {title: '三线单次价格', field: 'priceOnce3', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
Project.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Project.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加项目管理
 */
Project.openAddProject = function () {
    var index = layer.open({
        type: 2,
        title: '添加项目',
        area: ['1000px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/project/project_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看项目管理详情
 */
Project.openProjectDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '项目详情',
            area: ['1000px', '560px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/project/project_update/' + Project.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除项目管理
 */
Project.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/project/delete", function (data) {
            Feng.success("删除成功!");
            Project.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("projectId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询项目管理列表
 */
Project.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Project.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Project.initColumn();
    var table = new BSTable(Project.id, "/project/list", defaultColunms);
    table.setPaginationType("client");
    Project.table = table.init();
});
