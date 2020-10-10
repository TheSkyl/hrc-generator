/**
 * 初始化康复方案详情对话框
 */
var RehabilitationProgramInfoDlg = {
    rehabilitationProgramInfoData : {}
};

/**
 * 清除数据
 */
RehabilitationProgramInfoDlg.clearData = function() {
    this.rehabilitationProgramInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RehabilitationProgramInfoDlg.set = function(key, val) {
    this.rehabilitationProgramInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RehabilitationProgramInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RehabilitationProgramInfoDlg.close = function() {
    parent.layer.close(window.parent.RehabilitationProgram.layerIndex);
}

/**
 * 收集数据
 */
RehabilitationProgramInfoDlg.collectData = function() {
    this
    .set('id')
    .set('member')
    .set('disease')
    .set('description')
    .set('curativeEffect')
    .set('remark')
    .set('department')
    .set('createdBy')
    .set('createTime')
    .set('flag');
}

/**
 * 提交添加
 */
RehabilitationProgramInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    var att = [];
    $('.att-link').each(function (){
        att.push($(this).attr('href'));
    });
    this.set('attachment',att);

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rehabilitationProgram/add", function(data){
        Feng.success("添加成功!");
        window.parent.RehabilitationProgram.table.refresh();
        RehabilitationProgramInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rehabilitationProgramInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RehabilitationProgramInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rehabilitationProgram/update", function(data){
        Feng.success("修改成功!");
        window.parent.RehabilitationProgram.table.refresh();
        RehabilitationProgramInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rehabilitationProgramInfoData);
    ajax.start();
}

$(function() {
    var hrUp = new $MultiDocUpload("doc");
    hrUp.setUploadBarId("progressBar");
    hrUp.init();
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
    var patt = $("#patt").val();
    if(patt&&patt.length>0){
        $.each(patt.split(','),function(idx,dt){
            var $att = $('<div class="att-pane"><a class="att-link" target="_blank" href="'+dt+'">查看文档</a>&nbsp;&nbsp;<a href="javascript:void(0);" class="att-del">删除</a> </div>')
            $("#docPreId").append($att);
        })
    }
    $("input#memberValue").on("onSetSelectValue", function (event, result) {
        $('#member').val(result['id']);
    });

    $('select#solution').multiselect('refresh');
    $('select#solution').change(function () {
        $('#description').text('');
        $($(this).val()).each(function(i,dt){
            $('#description').append(dt+"&#13;&#10;")
        })
    })

    // $("#category").change(function () {
    //     var category = $(this).val();
    //     $.post("/healthProgram/programByCategory",{category:category},function(result){
    //         $('#symptom').empty();
    //         $('#symptom').append($('<option>').val('').text(' -- 请选择症状 -- '));
    //         $.each(result,function(i,dt){
    //             $('#symptom').append($('<option>').val(dt.value).text(dt.key));
    //         });
    //     });
    // });
    // var slen = $('select#solution optgroup').length;
    // $('select#solution optgroup').each(function (index,opg) {
    //     $opg=$('select#solution optgroup').eq(index);
    //     var opid = $opg.attr('data-id');
    //     $.post("/rehabilitationProgram/projectsByCategory",{pc:opid},function(result){
    //         $.each(result,function(i,dt){
    //             $opg.append($('<option>').val(dt.solution).text(dt.name));
    //         });
    //         if(index == slen-1){
    //             $('select#solution').multiselect();
    //         }
    //     });
    // });

    // $("#symptom").change(function () {
    //     var symptom = $(this).val();
    //    $('#description').text(symptom);
    // });
});
