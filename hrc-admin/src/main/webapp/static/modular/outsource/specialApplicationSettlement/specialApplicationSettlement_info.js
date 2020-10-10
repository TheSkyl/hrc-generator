/**
 * 初始化特殊申请结算详情对话框
 */
var SpecialApplicationSettlementInfoDlg = {
    specialApplicationSettlementInfoData : {}
};

/**
 * 清除数据
 */
SpecialApplicationSettlementInfoDlg.clearData = function() {
    this.specialApplicationSettlementInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialApplicationSettlementInfoDlg.set = function(key, val) {
    this.specialApplicationSettlementInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialApplicationSettlementInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SpecialApplicationSettlementInfoDlg.close = function() {
    parent.layer.close(window.parent.SpecialApplicationSettlement.layerIndex);
}

/**
 * 收集数据
 */
SpecialApplicationSettlementInfoDlg.collectData = function() {
    this
    .set('id')
    .set('specialApplication')
    .set('amount')
    .set('payType')
    .set('paymentDescription')
    .set('payee')
    .set('payTime')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
SpecialApplicationSettlementInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/specialApplicationSettlement/add", function(data){
        Feng.success("添加成功!");
        window.parent.SpecialApplicationSettlement.table.refresh();
        SpecialApplicationSettlementInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialApplicationSettlementInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SpecialApplicationSettlementInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/specialApplicationSettlement/update", function(data){
        Feng.success("修改成功!");
        window.parent.SpecialApplicationSettlement.table.refresh();
        SpecialApplicationSettlementInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialApplicationSettlementInfoData);
    ajax.start();
}

$(function() {
    var numberBsSuggest = $("#numberValue").bsSuggest({
        idField: 'said',
        keyField: 'number',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {said: "编号", member: "会员姓名", number: "申请单号"},
        showHeader: true,
        url: "/specialApplication/suggestList/",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"said": json[i]['id'], "member": json[i]['member'], "number": json[i]['number']})
            }
            return data
        }
    });
    $("input#numberValue").on("onSetSelectValue", function (event, result) {
        $('#specialApplication').val(result['id']);
        $.post("/specialApplicationSettlement/paymentInfo",{said:result['id']},function(result){
        $('#memberName').html(result['member']);
         $('#payedAmount').html(result['payed']);
         $('#payAmount').html(result['cost']);
         $('#amount').val(result['amount']);
    });
        });

});
