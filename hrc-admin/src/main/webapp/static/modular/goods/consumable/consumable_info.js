/**
 * 初始化耗材管理详情对话框
 */
var ConsumableInfoDlg = {
    consumableInfoData: {}
};

/**
 * 清除数据
 */
ConsumableInfoDlg.clearData = function () {
    this.consumableInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumableInfoDlg.set = function (key, val) {
    this.consumableInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumableInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ConsumableInfoDlg.close = function () {
    parent.layer.close(window.parent.Consumable.layerIndex);
}

/**
 * 收集数据
 */
ConsumableInfoDlg.collectData = function () {
    this
        .set('id')
        .set('name')
        .set('number')
        .set('specification')
        .set('price')
        .set('measureUnit')
        .set('manufacturer')
        .set('department')
        .set('amount')
        .set('createTime');
}

/**
 * 提交添加
 */
ConsumableInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumable/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Consumable.table.refresh();
        ConsumableInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumableInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ConsumableInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumable/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Consumable.table.refresh();
        ConsumableInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumableInfoData);
    ajax.start();
}

/**
 * 提交调库申请
 */
ConsumableInfoDlg.transferSubmit = function () {
    var destDepartment = $('#destDepartment').val();
    var srcDepartment = $('#srcDepartment').val();
    console.log("destDepartment",destDepartment);
    console.log("srcDepartment",srcDepartment);
    if(destDepartment && srcDepartment && srcDepartment==destDepartment){
        Feng.error("调库的提供门店和接收门店不能相同");
        return;
    }
    this.clearData();
    // this.collectData();
    this.set("id");
    this.set("srcDepartment");
    this.set("destDepartment");
    this.set("amount");
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumable/transfer", function (data) {
        Feng.success("调库成功!");
        window.parent.Consumable.table.refresh();
        ConsumableInfoDlg.close();
    }, function (data) {
        Feng.error("调库失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumableInfoData);
    ajax.start();
}

$(function () {
    // $('#amount').attr('data-placeholder','可填写正数或负数');
    if ($("#departmentValue")) {
        var consumableDeptBsSuggest = $("#departmentValue").bsSuggest({
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
            $.post("/mgr/userByDepartment", {dept: result['id']}, function (result) {
                $('#technician').empty();
                $.each(result, function (i, dt) {
                    $('#technician').append($('<option>').val(dt.value).text(dt.key));
                });
            });
        });
    }
    var consumableDestDeptBsSuggest = $("#destDepartmentValue").bsSuggest({
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
    $("input#destDepartmentValue").on("onSetSelectValue", function (event, result) {
        $('#destDepartment').val(result['id']);
        // $.post("/mgr/userByDepartment", {dept: result['id']}, function (result) {
        //     $('#technician').empty();
        //     $.each(result, function (i, dt) {
        //         $('#technician').append($('<option>').val(dt.value).text(dt.key));
        //     });
        // });
    });
});
