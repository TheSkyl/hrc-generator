@/*
    文件上传参数的说明:
    name : 名称
    id : 头像的id
@*/
<style type="text/css">
    .att-pane {
        font-size: 15px;
    }
</style>
<div class="form-group">
    <label class="col-sm-3 control-label head-scu-label">${name}</label>
    <div class="col-sm-6">
        <div id="${id}PreId">

        </div>
    </div><br/><br/>
    <div class="col-sm-3">
        <div class="upload-btn" id="${id}BtnId">
            <i class="fa fa-upload"></i>&nbsp;上传
        </div>
    </div>
    <input type="hidden" id="${id}" value="${iconImg!}"/>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


