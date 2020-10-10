/**
 * 健康档案管理初始化
 */
var MemberHealthRecord = {
    id: "MemberHealthRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberHealthRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '会员姓名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '电话号码', field: 'memberPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '健康状况评估', field: 'evaluation', visible: false, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '建档门店', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
            {title: '操作员', field: 'createdByName', visible: true, align: 'center', valign: 'middle'},
            {title: '建档时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberHealthRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberHealthRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加健康档案
 */
MemberHealthRecord.openAddMemberHealthRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加健康档案',
        area: ['1000px', '650px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberHealthRecord/memberHealthRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看健康档案详情
 */
MemberHealthRecord.openMemberHealthRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '健康档案详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberHealthRecord/memberHealthRecord_detail/' + MemberHealthRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除健康档案
 */
MemberHealthRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/memberHealthRecord/delete", function (data) {
            Feng.success("删除成功!");
            MemberHealthRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberHealthRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询健康档案列表
 */
MemberHealthRecord.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MemberHealthRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MemberHealthRecord.initColumn();
    var table = new BSTable(MemberHealthRecord.id, "/memberHealthRecord/list", defaultColunms);
    table.setPaginationType("client");
    MemberHealthRecord.table = table.init();
});
