/**
 * 积分管理初始化
 */
var Point = {
    id: "PointTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Point.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '会员', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '积分类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '积分数量', field: 'number', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Point.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Point.seItem = selected[0];
        return true;
    }
};

// /**
//  * 点击添加积分
//  */
// Point.openAddPoint = function () {
//     var index = layer.open({
//         type: 2,
//         title: '添加积分',
//         area: ['800px', '420px'], //宽高
//         fix: false, //不固定
//         maxmin: true,
//         content: Feng.ctxPath + '/point/point_add'
//     });
//     this.layerIndex = index;
// };
//
// /**
//  * 打开查看积分详情
//  */
// Point.openPointDetail = function () {
//     if (this.check()) {
//         var index = layer.open({
//             type: 2,
//             title: '积分详情',
//             area: ['800px', '420px'], //宽高
//             fix: false, //不固定
//             maxmin: true,
//             content: Feng.ctxPath + '/point/point_update/' + Point.seItem.id
//         });
//         this.layerIndex = index;
//     }
// };
//
// /**
//  * 删除积分
//  */
// Point.delete = function () {
//     if (this.check()) {
//         var ajax = new $ax(Feng.ctxPath + "/point/delete", function (data) {
//             Feng.success("删除成功!");
//             Point.table.refresh();
//         }, function (data) {
//             Feng.error("删除失败!" + data.responseJSON.message + "!");
//         });
//         ajax.set("pointId",this.seItem.id);
//         ajax.start();
//     }
// };

/**
 * 查询积分列表
 */
Point.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Point.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Point.initColumn();
    var table = new BSTable(Point.id, "/point/list", defaultColunms);
    table.setPaginationType("client");
    Point.table = table.init();
});
