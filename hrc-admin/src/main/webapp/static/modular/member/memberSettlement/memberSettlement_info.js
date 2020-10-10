/**
 * 初始化会员结算详情对话框
 */
var MemberSettlementInfoDlg = {
    memberSettlementInfoData : {}
};

/**
 * 清除数据
 */
MemberSettlementInfoDlg.clearData = function() {
    this.memberSettlementInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberSettlementInfoDlg.set = function(key, val) {
    this.memberSettlementInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberSettlementInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberSettlementInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberSettlement.layerIndex);
}

/**
 * 收集数据
 */
MemberSettlementInfoDlg.collectData = function() {
    this
    .set('id')
    .set('treatment')
    .set('paymentMethod')
    .set('paymentAmount')
    .set('memberRating')
    .set('foregroundRating')
    .set('technicianRating')
    .set('managerRating')
    .set('membershipCard')
    .set('member')
    .set('status')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
MemberSettlementInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    var cs = new Array();
    var flag = true;    // true继续;false取消
    $("#consumablePane").find(" > div").each(function (i,ele) {
        var c = new Object();
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
    this.set("consumable",JSON.stringify(cs));

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberSettlement/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberSettlement.table.refresh();
        MemberSettlementInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberSettlementInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberSettlementInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberSettlement/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberSettlement.table.refresh();
        MemberSettlementInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberSettlementInfoData);
    ajax.start();
}
MemberSettlementInfoDlg.addConsumable = function() {
    var dept = $('#department').val();
    if(dept==''){
        Feng.error("请选择所在门店!");
        return;
    }
    $('#consumablePane').append("<div class=\"col-sm-12\"><div class=\"input-group col-sm-9\" style=\"float:left;\"><input type=\"text\" class=\"form-control consumableName\" placeholder=\"请选择耗材\"><input type='hidden' class='consumableValue'/> <input type='hidden' class='consumableAvailable'/> <div class=\"input-group-btn\"><button type=\"button\" class=\"btn btn-white dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"caret\"></span></button><ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul></div><input type=\"text\" class=\"form-control consumableAmount\" placeholder=\"请输入数量\"></div><button type=\"button\" class=\"btn btn-danger delete-consumable\" onclick=\"MemberSettlementInfoDlg.deleteConsumable(event)\" class=\"delete-consumable\"><i class=\"fa fa-remove\"></i>&nbsp;删除</button></div>");
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

$(function() {

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
        $.post("/treatment/treatmentByMember",{member:result['id']},function(dt){
            // var dt = JSON.parse(result);
            if(dt.length>0){
                $('.treatment-pane').show();
            }
            $.each(dt,function(idx,item){
                $('#treatment').append('<option value="'+item['id']+'">'+item['projects']+'('+item['time']+')</option>');
            });
        });
    });
    var memberSettlementBsSuggest = $("#treatmentValue").bsSuggest({
        idField: 'treatment',
        keyField: 'name',
        allowNoKeyword: false,
        multiWord: true,
        separator: ",",
        getDataMethod: "url",
        effectiveFieldsAlias: {treatment: "编号", name: "姓名",projects: "康护项目",time: "康护时间"},
        showHeader: true,
        url: "/treatment/suggestList/",
        processData: function (json) {
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({"treatment": json[i]['id'], "name": json[i]['name'], "projects": json[i]['projects'], "time": json[i]['time']})
            }
            return data
        }
    });
    $("input#treatmentValue").on("onSetSelectValue", function (event, result) {
        $('#treatment').val(result['id']);
        $.post("/memberSettlement/experienced",{treatment:result['id']},function(r){
            // var r = JSON.parse(result);
            if(r.exped!=1){    // 已经体验过该项目
                $('#paymentMethod').find('option[value="0"]').remove();
            }
        });
        $.post("/treatment/projById",{treatment:result['id']},function(r){
            $('#projs').text(r.projects);
            $('#treatmentTime').text(r.time);
        });
    });

    $('#paymentMethod').change(function () {
        var pm = $(this).val();
        if(!pm||pm.length==0){
            return;
        }
        var tid = $('#treatment').val();
        var member = $('#member').val();
        // if(!tid||tid.length==0){
        //     Feng.error("请选择康复会员!");
        //     $(this).val('');
        //     return;
        // }
        var $mc = $('#membershipCard');

        // 会员卡选择
        if(pm==1){
            $.post("/memberSettlement/cardByTreatment",{treatment:tid,member:member},function(result){
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
        calcPrice(1,mc);
    });
    $('.mcpane').hide();
});

function calcPrice(pm,mc) {
    var tid = $('#treatment').val();
    var member = $('#member').val();
    // if(!tid||tid.length==0){
    //     Feng.error("请选择康复会员!");
    //     $('#paymentMethod').val('');
    //     return;
    // }
    var cs = new Array();
    var flag = true;    // true继续;false取消
    $("#consumablePane").find(" > div").each(function (i,ele) {
        var c = new Object();
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
    // 计算价格
    $.post("/memberSettlement/calcPrice",{treatment:tid,paymentMethod:pm,member:member,membershipCard:mc,consumable:JSON.stringify(cs)},function(result){
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

    // var dept = $('#department').val();
    // if(dept==''){
    //     Feng.error("请选择所在门店!");
    //     return;
    // }
    // $('#consumablePane').append("<div class=\"col-sm-12\"><div class=\"input-group col-sm-9\" style=\"float:left;\"><input type=\"text\" class=\"form-control consumableName\" placeholder=\"请选择耗材\"><input type='hidden' class='consumableValue'/> <input type='hidden' class='consumableAvailable'/> <div class=\"input-group-btn\"><button type=\"button\" class=\"btn btn-white dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"caret\"></span></button><ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul></div><input type=\"text\" class=\"form-control consumableAmount\" placeholder=\"请输入数量\"></div><button type=\"button\" class=\"btn btn-danger delete-consumable\" onclick=\"MemberSettlementInfoDlg.deleteConsumable(event)\" class=\"delete-consumable\"><i class=\"fa fa-remove\"></i>&nbsp;删除</button></div>");
    // $(".consumableName").bsSuggest({
    //     idField: 'consumable',
    //     keyField: 'name',
    //     allowNoKeyword: false,
    //     multiWord: true,
    //     separator: ",",
    //     getDataMethod: "url",
    //     effectiveFieldsAlias: {consumable: "编号", name: "名称",available:"可用数量"},
    //     showHeader: true,
    //     url: "/consumable/suggestList/"+dept+"/",
    //     listAlign: "left",
    //     processData: function (json) {
    //         var i, len, data = {value: []};
    //         if (!json || json.length == 0) {
    //             return false
    //         }
    //         len = json.length;
    //         for (i = 0; i < len; i++) {
    //             data.value.push({"consumable": json[i]['id'], "name": json[i]['name'], "available": json[i]['available']})
    //         }
    //         return data
    //     }
    // });
    // $("input.consumableName").on("onSetSelectValue", function (event, result) {
    //     var id = result['id'];
    //     var selected = $("input.consumableValue[value='"+id+"']").length>0;
    //     if(selected){
    //         Feng.error("您已选择了该耗材!");
    //         $(this).val('');
    //         return;
    //     }
    //     var $ca = $(this).siblings('input.consumableAvailable');
    //     $(this).siblings('input.consumableValue').val(id);
    //     $.post("/consumable/available",{c:id},function(result){
    //         $ca.val(result.available);
    //     });
    // });
    // $("input.consumableAmount").change(function () {
    //     var ca = parseInt($(this).val());
    //     var available = parseInt($(this).parent().children("input.consumableAvailable").first().val());
    //     if(ca>available){
    //         Feng.error("库存耗材数量不足,请确认!");
    //         $(this).val('');
    //     }
    // });
    // $(".delete-consumable").click(function () {
    //     // Feng.confirm("是否删除该耗材?", new function () {
    //     $(this).parent().remove();
    //     // });
    // });
}
