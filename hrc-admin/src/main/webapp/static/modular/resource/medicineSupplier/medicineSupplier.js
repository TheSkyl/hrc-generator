/**
 * 产品供应商管理初始化
 */
var MedicineSupplier = {
    id: "MedicineSupplierTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MedicineSupplier.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '供应商名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '类别', field: 'categoryName', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'contact', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人手机号', field: 'contactPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '法人', field: 'legalPerson', visible: true, align: 'center', valign: 'middle'},
            {title: '开户行', field: 'bank', visible: true, align: 'center', valign: 'middle'},
            {title: '开户行卡号', field: 'accountNumber', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MedicineSupplier.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MedicineSupplier.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加产品供应商
 */
MedicineSupplier.openAddMedicineSupplier = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品供应商',
        area: ['1000px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/medicineSupplier/medicineSupplier_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看产品供应商详情
 */
MedicineSupplier.openMedicineSupplierDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '产品供应商详情',
            area: ['1000px', '560px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/medicineSupplier/medicineSupplier_update/' + MedicineSupplier.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除产品供应商
 */
MedicineSupplier.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/medicineSupplier/delete", function (data) {
            Feng.success("删除成功!");
            MedicineSupplier.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("medicineSupplierId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询产品供应商列表
 */
MedicineSupplier.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MedicineSupplier.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MedicineSupplier.initColumn();
    var table = new BSTable(MedicineSupplier.id, "/medicineSupplier/list", defaultColunms);
    table.setPaginationType("client");
    MedicineSupplier.table = table.init();
});
