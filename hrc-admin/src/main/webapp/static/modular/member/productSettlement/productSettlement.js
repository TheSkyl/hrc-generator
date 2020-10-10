/**
 * 产品结算管理初始化
 */
var ProductSettlement = {
    id: "ProductSettlementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProductSettlement.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '产品记录', field: 'product', visible: true, align: 'center', valign: 'middle'},
            {title: '支付方式', field: 'paymentMethod', visible: true, align: 'center', valign: 'middle'},
            {title: '结算会员卡', field: 'membershipCard', visible: true, align: 'center', valign: 'middle'},
            {title: '支付金额', field: 'paymentAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '结算状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '结算时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'flag', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ProductSettlement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ProductSettlement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加产品结算
 */
ProductSettlement.openAddProductSettlement = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品结算',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/productSettlement/productSettlement_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看产品结算详情
 */
ProductSettlement.openProductSettlementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '产品结算详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/productSettlement/productSettlement_update/' + ProductSettlement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除产品结算
 */
ProductSettlement.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/productSettlement/delete", function (data) {
            Feng.success("删除成功!");
            ProductSettlement.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("productSettlementId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询产品结算列表
 */
ProductSettlement.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ProductSettlement.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ProductSettlement.initColumn();
    var table = new BSTable(ProductSettlement.id, "/productSettlement/list", defaultColunms);
    table.setPaginationType("client");
    ProductSettlement.table = table.init();
});
