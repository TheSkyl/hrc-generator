/**
 * 初始化产品结算详情对话框
 */
var ProductSettlementInfoDlg = {
    productSettlementInfoData : {}
};

/**
 * 清除数据
 */
ProductSettlementInfoDlg.clearData = function() {
    this.productSettlementInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductSettlementInfoDlg.set = function(key, val) {
    this.productSettlementInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductSettlementInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProductSettlementInfoDlg.close = function() {
    parent.layer.close(window.parent.ProductSettlement.layerIndex);
}

/**
 * 收集数据
 */
ProductSettlementInfoDlg.collectData = function() {
    this
    .set('id')
    .set('product')
    .set('paymentMethod')
    .set('membershipCard')
    .set('paymentAmount')
    .set('status')
    .set('createTime')
    .set('flag');
}

/**
 * 关闭此对话框
 */
ProductSettlementInfoDlg.addConsumable = function() {
    var dept = $('#department').val();
    if(dept==''){
        Feng.error("请选择所在门店!");
        return;
    }
    $('#consumablePane').append("<div class=\"col-sm-12\"><div class=\"input-group col-sm-9\" style=\"float:left;\"><input type=\"text\" class=\"form-control consumableName\" placeholder=\"请选择耗材\"><input type='hidden' class='consumableValue'/> <input type='hidden' class='consumableAvailable'/> <div class=\"input-group-btn\"><button type=\"button\" class=\"btn btn-white dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"caret\"></span></button><ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul></div><input type=\"text\" class=\"form-control consumableAmount\" placeholder=\"请输入数量\"></div><button type=\"button\" class=\"btn btn-danger delete-consumable\" onclick=\"DictInfoDlg.deleteConsumable(event)\" class=\"delete-consumable\"><i class=\"fa fa-remove\"></i>&nbsp;删除</button></div>");
    $(".consumableName").bsSuggest({
        idField: 'consumable',
        keyField: 'name',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {consumable: "编号", name: "名称",available:"可用数量"},
        showHeader: true,
        url: "/consumable/suggestList/"+dept+"/",
        listAlign: "left",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"consumable": json[i]['id'], "name": json[i]['name'], "available": json[i]['available']})
            }
            return data
        }
    });
    $("input.consumableName").on("onSetSelectValue", function (event, result) {
        var id = result['id'];
        var selected = $("input.consumableValue[value='"+id+"']").length>0;
        if(selected){
            Feng.error("您已选择了该耗材!");
            $(this).val('');
            return;
        }
        var $ca = $(this).siblings('input.consumableAvailable');
        $(this).siblings('input.consumableValue').val(id);
        $.post("/consumable/available",{c:id},function(result){
            $ca.val(result.available);
        });
    });
    $("input.consumableAmount").change(function () {
        var ca = parseInt($(this).val());
        var available = parseInt($(this).parent().children("input.consumableAvailable").first().val());
        if(ca>available){
            Feng.error("库存耗材数量不足,请确认!");
            $(this).val('');
        }
    });
    $(".delete-consumable").click(function () {
        // Feng.confirm("是否删除该耗材?", new function () {
        $(this).parent().remove();
        // });
    });
}

/**
 * 提交添加
 */
ProductSettlementInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/productSettlement/add", function(data){
        Feng.success("添加成功!");
        window.parent.ProductSettlement.table.refresh();
        ProductSettlementInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productSettlementInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProductSettlementInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/productSettlement/update", function(data){
        Feng.success("修改成功!");
        window.parent.ProductSettlement.table.refresh();
        ProductSettlementInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productSettlementInfoData);
    ajax.start();
}

$(function() {
    var dept = $('#department').val();
    if(dept==''){
        Feng.error("请选择所在门店!");
        return;
    }
    $('#consumablePane').append("<div class=\"col-sm-12\"><div class=\"input-group col-sm-9\" style=\"float:left;\"><input type=\"text\" class=\"form-control consumableName\" placeholder=\"请选择耗材\"><input type='hidden' class='consumableValue'/> <input type='hidden' class='consumableAvailable'/> <div class=\"input-group-btn\"><button type=\"button\" class=\"btn btn-white dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"caret\"></span></button><ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul></div><input type=\"text\" class=\"form-control consumableAmount\" placeholder=\"请输入数量\"></div><button type=\"button\" class=\"btn btn-danger delete-consumable\" onclick=\"DictInfoDlg.deleteConsumable(event)\" class=\"delete-consumable\"><i class=\"fa fa-remove\"></i>&nbsp;删除</button></div>");
    $(".consumableName").bsSuggest({
        idField: 'consumable',
        keyField: 'name',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {consumable: "编号", name: "名称",available:"可用数量"},
        showHeader: true,
        url: "/consumable/suggestList/"+dept+"/",
        listAlign: "left",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"consumable": json[i]['id'], "name": json[i]['name'], "available": json[i]['available']})
            }
            return data
        }
    });
    $("input.consumableName").on("onSetSelectValue", function (event, result) {
        var id = result['id'];
        var selected = $("input.consumableValue[value='"+id+"']").length>0;
        if(selected){
            Feng.error("您已选择了该耗材!");
            $(this).val('');
            return;
        }
        var $ca = $(this).siblings('input.consumableAvailable');
        $(this).siblings('input.consumableValue').val(id);
        $.post("/consumable/available",{c:id},function(result){
            $ca.val(result.available);
        });
    });
    $("input.consumableAmount").change(function () {
        var ca = parseInt($(this).val());
        var available = parseInt($(this).parent().children("input.consumableAvailable").first().val());
        if(ca>available){
            Feng.error("库存耗材数量不足,请确认!");
            $(this).val('');
        }
    });
    $(".delete-consumable").click(function () {
        // Feng.confirm("是否删除该耗材?", new function () {
        $(this).parent().remove();
        // });
    });
    var productSettlementDeptBsSuggest = $("#departmentValue").bsSuggest({
        idField: 'dept',
        keyField: 'name',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {dept: "编号", name: "名称"},
        showHeader: true,
        url: "/dept/suggestList/",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"dept": json[i]['id'], "name": json[i]['name']})
            }
            return data
        }
    });
    $("input#departmentValue").on("onSetSelectValue", function (event, result) {
        $('#department').val(result['id']);
        $.post("/mgr/userByDepartment",{dept:result['id']},function(result){
            $('#technician').empty();
            $.each(result,function(i,dt){
                $('#technician').append($('<option>').val(dt.value).text(dt.key));
            });
        });
    });
    var memberBsSuggest = $("#memberValue").bsSuggest({
        idField: 'member',
        keyField: 'name',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {member: "编号", name: "姓名", mobile: "手机号"},
        showHeader: true,
        url: "/member/suggestList/",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"member": json[i]['id'], "name": json[i]['name'], "mobile": json[i]['mobile']})
            }
            return data
        }
    });
    $("input#memberValue").on("onSetSelectValue", function (event, result) {
        $('#member').val(result['id']);
        $.post("/member/infoById",{id:result['id']},function(result){
            $('#memmobile').text(result.phone);
        });
    });
    $('#paymentMethod').change(function () {
        var pm = $(this).val();
        if(!pm||pm.length==0){
            return;
        }
        var member = $('#member').val();
        if(!member||member.length==0){
            Feng.error("请选择结算会员!");
            $(this).val('');
            return;
        }
        var $mc = $('#membershipCard');

        // 会员卡选择
        if(pm==1){
            $.post("/membershipCard/cardByMember",{member:member},function(result){
                // var r = JSON.parse(result);
                if(result.status!=1){
                    Feng.error(result.message);
                }else{
                    if(result.message !=null && result.message.length>0){
                        Feng.info(result.message);
                    }

                    $mc.empty();
                    $mc.append($('<option>').val('').text('请选择会员卡'));
                    $.each(result.data,function(i,dt){
                        $mc.append($('<option>').val(dt.value).text(dt.key));
                    });
                    $('.mcpane').show();
                    $('#membershipCard').trigger('change');
                }
            });
            return;
        }else{
            $('.mcpane').hide();
            $mc.empty();
            $mc.append($('<option>').val('').text('请选择会员卡'));
        }
        calcPrice(pm,null);
    });

    $('#membershipCard').change(function () {
        var mc = $('#membershipCard').val();
        if(!mc || mc.length==0){
            $("#paymentAmount").val('');
            return;
        }
        // calcPrice(1,mc);
    });
    $('.mcpane').hide();
});

function calcPrice(pm,mc) {
    var member = $('#member').val();
    if(!member||member.length==0){
        Feng.error("请选择会员!");
        $('#paymentMethod').val('');
        return;
    }
    var c = new Object();
    var cs = new Array();
    var flag = true;    // true继续;false取消
    $("#consumablePane").find(" > div").each(function (i,ele) {
        var cv = $(ele).find(".consumableValue").val();
        var ca = $(ele).find(".consumableAmount").val();
        if(cv==''||ca==''||isNaN(cv)||isNaN(ca)){
            Feng.error('请检查耗材使用情况!');
            flag=false;
            return;
        }
        c.id=cv;
        c.amount=ca;
        cs.push(c);
    });
    if(!flag)
        return;
    // 计算价格
    $.post("/productSettlement/calcPrice",{member:member,paymentMethod:pm,membershipCard:mc,cs:JSON.stringify(cs)},function(result){
        // var r = JSON.parse(result);
        if(result.status!=1){
            $("#paymentAmount").val('');
            if(pm==1){
                $('#membershipCard').val('');
            }
            Feng.error(result.message);
        }else{
            if(result.message !=null && result.message.length>0){
                Feng.info(result.message);
            }
            $("#paymentAmount").val(result.price);
        }
    });
}
