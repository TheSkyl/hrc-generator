/**
 * 特殊申请管理初始化
 */
var SpecialApplication = {
    id: "SpecialApplicationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SpecialApplication.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '申请单号', field: 'applicationNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '会员姓名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '申请项目', field: 'projectName', visible: true, align: 'center', valign: 'middle'},
            {title: '申请部门', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '申请时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '申请人', field: 'createdByName', visible: true, align: 'center', valign: 'middle'},
            {title: '申请状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
SpecialApplication.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SpecialApplication.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加特殊申请
 */
SpecialApplication.openAddSpecialApplication = function () {
    var index = layer.open({
        type: 2,
        title: '添加特殊申请',
        area: ['1000px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/specialApplication/specialApplication_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看特殊申请详情
 */
SpecialApplication.openSpecialApplicationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '特殊申请详情',
            area: ['1000px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/specialApplication/specialApplication_update/' + SpecialApplication.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除特殊申请
 */
SpecialApplication.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/specialApplication/delete", function (data) {
            Feng.success("删除成功!");
            SpecialApplication.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("specialApplicationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询特殊申请列表
 */
SpecialApplication.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SpecialApplication.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SpecialApplication.initColumn();
    var table = new BSTable(SpecialApplication.id, "/specialApplication/list", defaultColunms);
    table.setPaginationType("client");
    SpecialApplication.table = table.init();
});
