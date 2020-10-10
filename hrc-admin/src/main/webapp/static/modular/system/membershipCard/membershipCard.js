/**
 * 会员卡管理初始化
 */
var MembershipCard = {
    id: "MembershipCardTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MembershipCard.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '会员姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '卡号', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '余额', field: 'balance', visible: true, align: 'center', valign: 'middle'},
            {title: '会员卡级别', field: 'levelName', visible: true, align: 'center', valign: 'middle'},
            {title: '会员折扣', field: 'discountText', visible: true, align: 'center', valign: 'middle'},
            {title: '绑定项目', field: 'projectName', visible: true, align: 'center', valign: 'middle'},
            {title: '过期时间', field: 'dueDate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建日期', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MembershipCard.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MembershipCard.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员卡
 */
MembershipCard.openAddMembershipCard = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员卡',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/membershipCard/membershipCard_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员卡详情
 */
MembershipCard.openMembershipCardDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员卡详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/membershipCard/membershipCard_update/' + MembershipCard.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员卡
 */
MembershipCard.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/membershipCard/delete", function (data) {
            Feng.success("删除成功!");
            MembershipCard.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("membershipCardId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员卡列表
 */
MembershipCard.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MembershipCard.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MembershipCard.initColumn();
    var table = new BSTable(MembershipCard.id, "/membershipCard/list", defaultColunms);
    table.setPaginationType("client");
    MembershipCard.table = table.init();
});
