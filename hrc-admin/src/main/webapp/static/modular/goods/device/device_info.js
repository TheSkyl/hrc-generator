/**
 * 初始化设备管理详情对话框
 */
var DeviceInfoDlg = {
    deviceInfoData : {}
};

/**
 * 清除数据
 */
DeviceInfoDlg.clearData = function() {
    this.deviceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeviceInfoDlg.set = function(key, val) {
    this.deviceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeviceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DeviceInfoDlg.close = function() {
    parent.layer.close(window.parent.Device.layerIndex);
}

/**
 * 收集数据
 */
DeviceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('deviceNumber')
    .set('deviceName')
    .set('price')
    .set('amount')
    .set('status')
    .set('purchaseTime')
    .set('department')
    .set('maintainer')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
DeviceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/device/add", function(data){
        Feng.success("添加成功!");
        window.parent.Device.table.refresh();
        DeviceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deviceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DeviceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/device/update", function(data){
        Feng.success("修改成功!");
        window.parent.Device.table.refresh();
        DeviceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deviceInfoData);
    ajax.start();
}

$(function() {
    $("#status").val($("#statusValue").val());

    var dDeptBsSuggest = $("#departmentValue").bsSuggest({
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
            $('#maintainer').empty();
            $.each(result,function(i,dt){
                $('#maintainer').append($('<option>').val(dt.value).text(dt.key));
            });
        });
    });
});
