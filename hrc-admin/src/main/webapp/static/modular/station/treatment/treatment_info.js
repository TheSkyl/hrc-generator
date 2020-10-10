/**
 * 初始化康护记录详情对话框
 */
var TreatmentInfoDlg = {
    treatmentInfoData : {}
};

/**
 * 清除数据
 */
TreatmentInfoDlg.clearData = function() {
    this.treatmentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TreatmentInfoDlg.set = function(key, val) {
    this.treatmentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TreatmentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TreatmentInfoDlg.close = function() {
    parent.layer.close(window.parent.Treatment.layerIndex);
}

/**
 * 关闭此对话框
 */
TreatmentInfoDlg.addConsumable = function() {
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
        if(selected>0){
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
 * 收集数据
 */
TreatmentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('department')
    .set('technician')
    .set('processDescription')
    .set('resultDescription');
}

/**
 * 提交添加
 */
TreatmentInfoDlg.addSubmit = function() {
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
    this.set("projects");

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/treatment/add", function(data){
        Feng.success("添加成功!");
        window.parent.Treatment.table.refresh();
        TreatmentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.treatmentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TreatmentInfoDlg.editSubmit = function() {

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
    this.set("projects");

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/treatment/update", function(data){
        Feng.success("修改成功!");
        window.parent.Treatment.table.refresh();
        TreatmentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.treatmentInfoData);
    ajax.start();
}
function reCalConsumable() {
    console.log('OK');
}

$(function() {
    $('#projects').attr('multiple','').attr('data-placeholder','请选择康护项目').addClass('chosen-select').val('');
    var config = {
        ".chosen-select": {},
        ".chosen-select-deselect": {allow_single_deselect: !0},
        ".chosen-select-no-single": {disable_search_threshold: 10},
        ".chosen-select-no-results": {no_results_text: "Oops, nothing found!"},
        ".chosen-select-width": {width: "95%"}
    };
    var $selp = $('#selp');
    if($selp.length>0 && $selp.val().length>0){
        $.each($selp.val().split(','), function(key, value) {
            $("#projects option[value='" + value + "']").prop("selected", true);
        });
    }
    for (var selector in config) $(selector).chosen(config[selector]).trigger("chosen:updated");
    var treatmentUserBsSuggest = $("#userIdValue").bsSuggest({
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
    $("input#userIdValue").on("onSetSelectValue", function (event, result) {
        $('#userId').val(result['id']);
    });

    var treatmentDeptBsSuggest = $("#departmentValue").bsSuggest({
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

    // var treatmentConsumableBsSuggest;
});
