/**
 * 特殊申请结算管理初始化
 */
var SpecialApplicationSettlement = {
    id: "SpecialApplicationSettlementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SpecialApplicationSettlement.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '申请单号', field: 'specialApplicationNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '支付方式', field: 'payTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '付款描述', field: 'paymentDescription', visible: true, align: 'center', valign: 'middle'},
            {title: '收款人', field: 'payee', visible: true, align: 'center', valign: 'middle'},
            {title: '收款时间', field: 'payTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结算时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SpecialApplicationSettlement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SpecialApplicationSettlement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加特殊申请结算
 */
SpecialApplicationSettlement.openAddSpecialApplicationSettlement = function () {
    var index = layer.open({
        type: 2,
        title: '添加特殊申请结算',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/specialApplicationSettlement/specialApplicationSettlement_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看特殊申请结算详情
 */
SpecialApplicationSettlement.openSpecialApplicationSettlementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '特殊申请结算详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/specialApplicationSettlement/specialApplicationSettlement_update/' + SpecialApplicationSettlement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除特殊申请结算
 */
SpecialApplicationSettlement.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/specialApplicationSettlement/delete", function (data) {
            Feng.success("删除成功!");
            SpecialApplicationSettlement.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("specialApplicationSettlementId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询特殊申请结算列表
 */
SpecialApplicationSettlement.search = function () {
    var queryData = {};
    queryData['specialApplicationNumber'] = $("#specialApplicationNumber").val();
    SpecialApplicationSettlement.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SpecialApplicationSettlement.initColumn();
    var table = new BSTable(SpecialApplicationSettlement.id, "/specialApplicationSettlement/list", defaultColunms);
    table.setPaginationType("client");
    SpecialApplicationSettlement.table = table.init();
});
