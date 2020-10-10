/**
 * 申请执行管理初始化
 */
var SpecialApplicationExec = {
    id: "SpecialApplicationExecTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SpecialApplicationExec.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '申请单号', field: 'specialApplicationNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '申请会员', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '费用', field: 'cost', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'contact', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '执行时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SpecialApplicationExec.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SpecialApplicationExec.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加申请执行
 */
SpecialApplicationExec.openAddSpecialApplicationExec = function () {
    var index = layer.open({
        type: 2,
        title: '添加申请执行',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/specialApplicationExec/specialApplicationExec_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看申请执行详情
 */
SpecialApplicationExec.openSpecialApplicationExecDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '申请执行详情',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/specialApplicationExec/specialApplicationExec_update/' + SpecialApplicationExec.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除申请执行
 */
SpecialApplicationExec.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/specialApplicationExec/delete", function (data) {
            Feng.success("删除成功!");
            SpecialApplicationExec.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("specialApplicationExecId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询申请执行列表
 */
SpecialApplicationExec.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SpecialApplicationExec.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SpecialApplicationExec.initColumn();
    var table = new BSTable(SpecialApplicationExec.id, "/specialApplicationExec/list", defaultColunms);
    table.setPaginationType("client");
    SpecialApplicationExec.table = table.init();
});
