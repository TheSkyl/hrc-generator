/**
 * 会员结算管理初始化
 */
var MemberSettlement = {
    id: "MemberSettlementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberSettlement.initColumn = function () {
    return [
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '会员姓名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '康护项目', field: 'projectNames', visible: true, align: 'center', valign: 'middle'},
            {title: '康护产品', field: 'productNames', visible: true, align: 'center', valign: 'middle'},
            {title: '支付方式', field: 'paymentMethodName', visible: true, align: 'center', valign: 'middle'},
            {title: '支付金额', field: 'paymentAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '顾客打分', field: 'memberRating', visible: true, align: 'center', valign: 'middle'},
            {title: '前台打分', field: 'foregroundRating', visible: true, align: 'center', valign: 'middle'},
            {title: '技师打分', field: 'technicianRating', visible: true, align: 'center', valign: 'middle'},
            {title: '经理打分', field: 'managerRating', visible: true, align: 'center', valign: 'middle'},
            {title: '结算时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberSettlement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberSettlement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员结算
 */
MemberSettlement.openAddMemberSettlement = function () {
    var index = layer.open({
        type: 2,
        title: '会员结算',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberSettlement/memberSettlement_add'
    });
    this.layerIndex = index;
};

/**
 * 点击添加会员结算
 */
MemberSettlement.openProductSettlement = function () {
    parent.$(".product-settlement").trigger("click");
};

/**
 * 打开查看会员结算详情
 */
MemberSettlement.openMemberSettlementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员结算详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberSettlement/memberSettlement_update/' + MemberSettlement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员结算
 */
MemberSettlement.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/memberSettlement/delete", function (data) {
            Feng.success("删除成功!");
            MemberSettlement.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberSettlementId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员结算列表
 */
MemberSettlement.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MemberSettlement.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MemberSettlement.initColumn();
    var table = new BSTable(MemberSettlement.id, "/memberSettlement/list", defaultColunms);
    table.setPaginationType("client");
    MemberSettlement.table = table.init();
});
