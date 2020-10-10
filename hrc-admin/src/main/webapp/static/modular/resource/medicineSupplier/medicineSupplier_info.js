/**
 * 初始化产品供应商详情对话框
 */
var MedicineSupplierInfoDlg = {
    medicineSupplierInfoData : {}
};

/**
 * 清除数据
 */
MedicineSupplierInfoDlg.clearData = function() {
    this.medicineSupplierInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MedicineSupplierInfoDlg.set = function(key, val) {
    this.medicineSupplierInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MedicineSupplierInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MedicineSupplierInfoDlg.close = function() {
    parent.layer.close(window.parent.MedicineSupplier.layerIndex);
}

/**
 * 收集数据
 */
MedicineSupplierInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('category')
    .set('address')
    .set('phone')
    .set('contact')
    .set('contactPhone')
    .set('legalPerson')
    .set('businessLicense')
    .set('bank')
    .set('accountNumber');
}

/**
 * 提交添加
 */
MedicineSupplierInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/medicineSupplier/add", function(data){
        Feng.success("添加成功!");
        window.parent.MedicineSupplier.table.refresh();
        MedicineSupplierInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.medicineSupplierInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MedicineSupplierInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/medicineSupplier/update", function(data){
        Feng.success("修改成功!");
        window.parent.MedicineSupplier.table.refresh();
        MedicineSupplierInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.medicineSupplierInfoData);
    ajax.start();
}

$(function() {
    // 初始化字典
    $("#category").val($("#categoryValue").val());
});
