/**
 * 初始化会员预约详情对话框
 */
var AppointmentInfoDlg = {
    appointmentInfoData : {}
};

/**
 * 清除数据
 */
AppointmentInfoDlg.clearData = function() {
    this.appointmentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppointmentInfoDlg.set = function(key, val) {
    this.appointmentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppointmentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AppointmentInfoDlg.close = function() {
    parent.layer.close(window.parent.Appointment.layerIndex);
}

/**
 * 收集数据
 */
AppointmentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('memberId')
    .set('project')
    .set('type')
    .set('startTime')
    .set('duration')
    .set('endTime')
    .set('department')
    .set('technician')
    .set('status')
    .set('memo')
    .set('created')
    .set('createTime');
}

/**
 * 提交添加
 */
AppointmentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    var pbhhr = $('#projectsByMHR').val();
    if(pbhhr){
        var projs = pbhhr.split(',');
        this.set("projects",projs);
    }else{
        this.set("projects");
    }


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appointment/add", function(data){
        if(data.code==200){
            Feng.success("添加成功!");
            window.parent.Appointment.table.refresh();
            AppointmentInfoDlg.close();
        }else{
            Feng.error(data.message);
        }

    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appointmentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AppointmentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    this.set("projects");

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appointment/update", function(data){
        if(data.code==200){
            Feng.success("修改成功!");
            window.parent.Appointment.table.refresh();
            AppointmentInfoDlg.close();
        }else{
            Feng.error(data.message);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appointmentInfoData);
    ajax.start();
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
    var memberBsSuggest = $("#member").bsSuggest({
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
    $("input#member").on("onSetSelectValue", function (event, result) {
        $('#memberId').val(result['id']);
        $.post("/memberHealthRecord/projectByMember",{member:result['id']},function(result){
            if(result.length>0){
                $('.mhr-pane').show();
                $('.oppane').hide();
                $.each(result,function(idx,r){
                    $("#projectsByMHR").append("<option value='"+r.id+"'>"+r.name+"</option>");
                })
            }
        });
    });
    $("#type").val($('#typeValue').val());
    $("#project").val($('#projectValue').val());
    $("#status").val($('#statusValue').val());
    $("#startTime").val($('#startTimeSpan').text());
    $("#endTime").val($('#endTimeSpan').text());
    var tid = $("#technician").attr('value');
    console.log(tid);
    $("#technician").find('option[value="'+tid+'"]').prop('selected',true);
});
