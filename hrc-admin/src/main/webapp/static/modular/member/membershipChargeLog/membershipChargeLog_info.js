/**
 * 初始化会员充值记录详情对话框
 */
var MembershipChargeLogInfoDlg = {
    membershipChargeLogInfoData : {}
};

/**
 * 清除数据
 */
MembershipChargeLogInfoDlg.clearData = function() {
    this.membershipChargeLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MembershipChargeLogInfoDlg.set = function(key, val) {
    this.membershipChargeLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MembershipChargeLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MembershipChargeLogInfoDlg.close = function() {
    parent.layer.close(window.parent.MembershipChargeLog.layerIndex);
}

/**
 * 收集数据
 */
MembershipChargeLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('card')
    .set('amount')
    .set('givenAmount')
    .set('department')
    .set('created')
    .set('memo')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
MembershipChargeLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/membershipChargeLog/add", function(result){
        if(result && result.code!=200){
            Feng.error(result.message);
        }else {
            Feng.success("添加成功!");
            window.parent.MembershipChargeLog.table.refresh();
            MembershipChargeLogInfoDlg.close();
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.membershipChargeLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MembershipChargeLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/membershipChargeLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.MembershipChargeLog.table.refresh();
        MembershipChargeLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.membershipChargeLogInfoData);
    ajax.start();
}

MembershipChargeLogInfoDlg.openAddMembershipCard = function () {
    var index = parent.layer.open({
        type: 2,
        title: '添加会员卡',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/membershipCard/membershipCard_add'
    });
    parent.layerIndex = index;
}

$(function() {
    var memberCardBsSuggest = $("#cardValue").bsSuggest({
        idField: 'id',
        keyField: 'number',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {id: "编号",number: "卡号", name: "姓名", mobile: "手机号"},
        showHeader: true,
        url: "/membershipCard/suggestList/",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"id": json[i]['id'],"number": json[i]['number'], "name": json[i]['name'], "mobile": json[i]['mobile']})
            }
            return data
        }
    });
    $("input#cardValue").on("onSetSelectValue", function (event, result) {
        $('#card').val(result['id']);
    });
    $("#created").val($('#userId').val());
    $("#department").val($('#departmentId').val());
    $("#department").change(function(){
        $.post("/mgr/userByDepartment",{dept:$("#department").val()},function(result){
            $('#created').empty();
            $.each(result,function(i,dt){
            $('#created').append($('<option>').val(dt.value).text(dt.key));
        });
    });
    });

    $("#btn-add-membership-card").click(function () {
        MembershipChargeLogInfoDlg.openAddMembershipCard();
    });

    $('#amount').on('input',function(){
        var amount = $(this).val();
        var membershipCard = $('#card').val();
        if(parseFloat(amount)>0 && parseInt(membershipCard)>0) {
            $.post("/membershipChargeLog/chargePresent", {
                amount: amount,
                membershipCard: membershipCard
            }, function (result) {
                if (result.code == 200) {
                    var dr = '';
                    if(result.data.level)
                        dr = "充值后金额为["+result.data.balance+"],等级为["+result.data.level+"], 赠送["+result.data.present+"]元"
                    $('#chargeTip').text(dr);
                } else {
                    Feng.info(result.message);
                }
            });
        }
    });
});
