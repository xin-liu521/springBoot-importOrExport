
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>店小二 --订单信息</title>

<#include "../common/css.ftl"/>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
<#include "../common/left.ftl"/>
    <!--左侧导航结束-->

    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">

        </div>
        <!--顶部导航开始-->
    <#include "../common/top.ftl"/>
        <!--顶部导航结束-->
        <div class="row J_mainContent" id="content-main">
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title"> <h5>计算记录列表</h5></div>
                            <div class="ibox-content">
                                <div class="main" id="main">
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col-md-6">
                                            <button id="btn-add" type="button" class="btn btn-info" data-toggle="modal" href="#addPageModal">新增</button>
                                        </div>
                                    </div>
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col-md-2">
                                            <input id="yearMonth" name="yearMonth" class="form-control datetimepicker" readonly  placeholder="月份选择">
                                        </div>
                                        <div class="col-md-2">
                                            <select class="form-control" id="regionIdOne" name="regionIdOne">
                                                <option value="">地区选择</option>
                                                <#list regionList as region>
                                                    <option value="${region.id!}">${region.name}</option>
                                                </#list>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control" id="operator" name="operator" placeholder="创建人">
                                        </div>

                                        <button id="btn-search" type="button" class="btn btn-success" onclick="searchMonth();">查询</button>
                                        <button id="btn-search" type="button" class="btn btn-default" onclick="searchClear();">清空</button>
                                    </div>
                                    <table id="orderTable" class="table table-striped table-bordered table-hover dataTables-example" >

                                    </table>
                                <#--</div>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="pull-right">  西安威沃网络科技有限公司 | Powered by
                <a class="a" href="http://www.weiwokeji.cn//"><font color="#999">www.weiwokeji.cn</font></a>
            </div>
        </div>
    </div>
</div>
<!-- 新增页面 -->
<div class="modal fade" id="addPageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 480px;">
            <div class="modal-content">
                <form id="addMonthForm">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title text-center" style="font-size: 2.5rem;">创建订单</h4>
                    </div>
                    <div class="modal-body" style="height: 140px;display: flex;justify-content:space-between;flex-direction: column;align-items: center">
                        <span style="width:252px;display: inline-block">
                            <input id="time" name="time" class="datetimepicker form-control" readonly style="border: 1px solid #ccc;width: 251px;" placeholder="请选择日期">
                        </span>
                        <span style="width:252px;display: inline-block;">
                            <select class="form-control" id="regionId" name="regionId">
                                <option value="">地区选择</option>
                                <#list regionList as region>
                                    <option value="${region.id!}">${region.name}</option>
                                </#list>
                            </select>
                        </span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-info" onclick="addMonth();">保存</button>
                    </div>
                </form>
            </div>
    </div>
</div>

<div class="modal fade" id="stationRank" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 480px;">
        <div class="modal-content">
            <form id="addMonthForm">
                <input type="hidden" id="rankRecordId" name="rankRecordId"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-center" style="font-size: 2.5rem;">创建星级</h4>
                </div>
                <div class="modal-body" style="height: auto;display: flex;justify-content:space-between;flex-direction: column;align-items: center">
                        <span style="width:252px;display: inline-block">
                            <select class="form-control" id="level" name="level">
                                <option value="0">4星站点</option>
                                <option value="1">5星站点</option>
                            </select>
                        </span>
                        <span id="checkboxId" style="width:252px;display: inline-block;line-height:2;padding:0 6px;margin-top:20px;display: flex;flex-wrap: wrap;justify-content: flex-start"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-info" onclick="addRank();">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="attendModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 480px;">
        <div class="modal-content">
            <form id="attendForm">
                <input type="hidden" id="monthId" name="monthId"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-center" style="font-size: 2.5rem;">创建星级</h4>
                </div>
                <div class="modal-body" style="height: auto;display: flex;justify-content:space-between;flex-direction: column;align-items: center">
                <span style="width:252px;display: inline-block">
                   <input type="text" class="form-control" id="attend" name="attend"/>
                </span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-info" onclick="addAttend();">保存</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
<#include "../common/js.ftl"/>
</html>
<script>

    toastr.options.positionClass = 'toast-top-center';
    var path = '${basePath}';
    <#--var regionId = '${regionId}';-->
    <#--var userId = '${userId}';-->
    //组装查询条件
    var queryParams = function (params) {
        var param = {
            pageNumber: params.pageNumber,
            pageSize: params.pageSize,
            region : $("#regionIdOne").val(),
            yearMonth : $("#yearMonth").val(),
            operator: $("#operator").val()
        };
        return param;
    };

    var $table=null;
    var $materialTable=null;
    $(function () {
        $table = $('#orderTable').bootstrapTable({
            method: 'post',
            cache: false, // 不缓存
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true,  // 隔行加亮
            pagination: true, //是否显示分页（*）
            pageSize: 10, //每页的记录行数（*）
            pageNumber:1, //初始化加载第一页，默认第一页
            pageList: [10, 20, 50, 100],
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            paginationFirstText: "首页",
            paginationLastText: "末页",
            showColumns: false,  //是否显示列的下拉列表控件
            showExport: true,
            clickToSelect: true, //是否启用点击选中行
            //singleSelect:true,//单选
            minimumCountColumns: 2,  //最少允许的列数 clickToSelect: true,
            queryParamsType:'undefined',//设置为"undefined",可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为"limit",符合 RESTFul 格式的参数,可以获取limit, offset, search, sort, order
            sidePagination: "server",
            url:path+"/monthRecord/queryMonthRecordList",
            exportTypes: ['json', 'xml', 'csv', 'txt', 'sql', 'excel'],
            dataType:"json",
            sortOrder : "desc",
            sortName : "create_time",
            queryParams : function(params){
                var q = queryParams(params);
                return q;
            },
            contentType: "application/x-www-form-urlencoded",
            columns: [
                {
                    checkbox:true,
                },
                {field:"id",title:"序号",align:"center",valign:"middle",visible:false},
                {field:"year",title:"工资计算年份",align:"center",valign:"middle",sortable:"true"},
                {field:"month",title:"工资计算月份",align:"center",valign:"middle",sortable:"true",
                    formatter : function(value, row, index){
                        if (value < 10) {
                            return "0"+value;
                        }else {
                            return value;
                        }
                    }
                },
                {field:"name",title:"地区",align:"center",valign:"middle",sortable:"true"},
                {field:"status",title:"状态",align:"center",valign:"middle",
                    formatter : function(value, row, index){
                        if (value == 0) {
                            return "未计算"
                        }
                        if (value == 1) {
                            return "计算中"
                        }
                        if (value == 2) {
                            return "计算完成"
                        }
                        if (value == 3) {
                            return "计算失败"
                        }
                    }
                },
                {field:"attend",title:"出勤天数",align:"center",valign:"middle",sortable:"true"},
                {field:"addtime",title:"创建时间",align:"center",valign:"middle",sortable:"true",formatter: dataFormatter},
                {field:"username",title:"创建人",align:"center",valign:"middle"},
                {field:"operate",title:"操作",valign:"middle",width : 600,
                    formatter: function(value, row, index){
                        var html = '<button class="btn btn-info btn-xs" onclick="importOrder('+index+')">订单详情</button> ' +
                                    '<button class="btn btn-info btn-xs" onclick="importAdjust('+index+')">各类统计</button> ' +
                                    '<button class="btn btn-info btn-xs" onclick="importUserInfo('+index+')">考勤详情</button> ';
                        if (row.status == 4) {
                            html += '<button class="btn btn-info btn-xs" disabled>开始计算</button> ';
                        }else {
                            html += '<button class="btn btn-info btn-xs"  onclick="startCalculate('+index+')">开始计算</button> ';
                        }
                        if (row.region == 1){
                            html += '<button class="btn btn-info btn-xs" onclick="resultDetail('+index+')">工资计算结果</button> '+
                            '<button class="btn btn-info btn-xs"  onclick="notarizeOrder('+index+')">确认</button> ';
                        }
                        if (row.region == 2) {
                            html += '<button class="btn btn-info btn-xs" onclick="oldResultDetail('+index+')">老骑手计算结果</button> '+
                                    '<button class="btn btn-info btn-xs" onclick="newResultDetail('+index+')">新骑手计算结果</button> ';
                        }
                        html += '<button class="btn btn-info btn-xs"  onclick="stationRank('+index+')">站点级别</button> ' +
                                '<button class="btn btn-info btn-xs"  onclick="attendDay('+index+')">出勤天数</button>';
                        return html;
                    }
                }
            ],
            onLoadSuccess: function (data) { //加载成功时执行
            },
            onLoadError: function (res) { //加载失败时执行
            }
        });

        $(window).resize(function () {
            $('#orderTable').bootstrapTable('resetView');
        });

        //初始化时间组件
        $(".datetimepicker").datetimepicker({
            language: 'zh-CN',         //语言选择中文
            timepicker: false,    //关闭时间选项
            yearStart: 2016,

            format: 'yyyy-mm',
            autoclose: true,
            todayBtn: true,
            startView: 'year',
            minView:'year',
            maxView:'decade',

            yearEnd: 2050,        //设置最大年份
            todayHighlight: true,
            //keyboardNavigation :true
        });

        //初始化时间组件
//        $("#year").datetimepicker({
//            language: 'zh-CN',         //语言选择中文
//            format: 'yyyy',
//            autoclose: 1,
//            startView: 4, //这里就设置了默认视图为年视图
//            minView: 4, //设置最小视图为年视图
//            forceParse: 0 ///选择后直接带回；要加！
//        });

        formValidator();

        $("#attendForm").validate({
            rules: {
                attend : {//姓名
                    required : true,
                    isNumber : true
                }

            },
            messages: {

            }

        });
    });


    //表单验证
    function formValidator(){
        $("#addMonthForm").validate({
            rules: {
                time : {
                    required : true,
                    remote : {
                        url: path+"/monthRecord/isMonthRecord",//校验是否存在
                        type: "post",
                        async: false,
                        dataType: "json",
                        data: {
                            "time": function () {
                                return $("#time").val();
                            },
                            "regionId": function () {
                                return $("#regionId").val();
                            },
                        },
                        dataFilter: function (data) {
                            var result = eval("(" + data + ")");
                            if (result.code == 0) {
                                return false;
                            } else{
                                return true;
                            }
                        }
                    }
                },
                regionId : {
                    required : true
                }
            },

            messages: {
                time : {
                    remote : "<span style='color: red;font-size: 13px;'>该地区该月份已存在</span>"
                }
            }
        });
    }

    function dataFormatter (value, row, index) {
        if (value == null) {
            return "";
        }else {
            return changeDateFormat(value);
        }
    }

    /**
     * @desc 初始时间格式
     * @param value
     */
    function changeDateFormat(time) {
        var date =new Date(time);
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var year = date.getFullYear();
        var hours = date.getHours();
        var minute= date.getMinutes();
        var second= date.getSeconds();
        if (month < 10) {
           var m = '0'+ month;
        }else {
            var m = month
        }
        if (day < 10) {
            var d = '0'+ day;
        }else {
            var d = day
        }
        if (hours < 10) {
            var h = '0'+ hours;
        }else {
            var h = hours
        }
        if (minute < 10) {
            var mi = '0'+ minute;
        }else {
            var mi = minute
        }
        if (second < 10) {
            var s = '0'+ second;
        }else {
            var s = second
        }
        return year+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
    }

    //重置验证
    function resetValid(){
        $("#addMonthForm").validate().resetForm(); //取消form验证
        formValidator();
    }

    function addMonth(){
        resetValid();
        if (!$("#addMonthForm").valid()) {
            return false;
        }else {
            $.ajax({
                url : path+"/monthRecord/addMonthRecord",
                type : "post",
                dataType : "json",
                data : {
                    "time" : $("#time").val(),
                    "regionId" : $("#regionId").val(),
//                    "userId" : userId
                },
                success : function(result){
                    $("#addPageModal").modal("hide");
                    resetValid();
                    if (result.code == 0) {
                        layer.msg("保存成功！");
                    }else {
                        layer.msg("保存失败！");
                    }
                    $('#orderTable').bootstrapTable('refresh');
                },
                error : function(){
                    $("#addPageModal").modal("hide");
                    layer.msg("系统错误！");
                }
            });
        }

    }

    //订单页面跳转
    function importOrder(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        window.location.href = path+'/salaryOrder/importExcelPage?id='+data.id+"&&year="+data.year+"&&month="+data.month+"&&region="+data.region;
    }

    //人效页面信息
    function importAdjust(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        window.location.href = path+'/orderAdjust/orderAdjustPage?id='+data.id+"&&year="+data.year+"&&month="+data.month+"&&region="+data.region;
    }

    //考勤信息页面
    function importUserInfo(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        window.location.href = path+'/salaryUserInfo/salaryUserInfoPage?id='+data.id+"&&year="+data.year+"&&month="+data.month+"&&region="+data.region;
    }

    //西安跳转结果页面
    function resultDetail(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        window.location.href = path+'/resultDetail/resultDetailPage?id='+data.id+"&&year="+data.year+"&&month="+data.month+"&&region="+data.region;
    }

     //北京跳转结果页面
    function oldResultDetail(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        window.location.href = path+'/resultDetail/oldResultDetailPage?id='+data.id+"&&year="+data.year+"&&month="+data.month+"&&region="+data.region;
    }

    //北京新骑手结果页面
    function newResultDetail(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        window.location.href = path+'/resultDetail/newResultDetailPage?id='+data.id+"&&year="+data.year+"&&month="+data.month+"&&region="+data.region;
    }


    function startCalculate(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据

        swal({
            title: "确认要开始计算吗?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            cancelButtonText: "取消",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function (isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : path+'/resultDetail/startCalculate/'+data.id+'/'+data.region,
                    type : 'post',
                    dataType : 'json',
                    success : function(result) {
                        //layer.closeAll('loading');
                        $('#orderTable').bootstrapTable('refresh');
                    },
                    error : function(){
                        //layer.closeAll('loading');
                        $('#orderTable').bootstrapTable('refresh');
                        layer.msg("系统错误！");
                    }
                });
            }
            $('#orderTable').bootstrapTable('refresh');
        });


    }

    //搜索
    function searchMonth(){
        $('#orderTable').bootstrapTable('refresh');
    }

    //搜索条件清空
    function searchClear(){
        $("#regionIdOne").val("");
        $("#yearMonth").val("")
        $("#operator").val("");
        $('#orderTable').bootstrapTable('refresh');
    }

    //确认操作
    function notarizeOrder(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据

        swal({
            title: "是否要确认操作吗?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            cancelButtonText: "取消",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function (isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : path+'/resultDetail/notarizeOrder/'+data.id,
                    type : 'post',
                    dataType : 'json',
                    success : function(result) {
                        //layer.closeAll('loading');
                        $('#orderTable').bootstrapTable('refresh');
                    },
                    error : function(){
                        //layer.closeAll('loading');
                        $('#orderTable').bootstrapTable('refresh');
                        layer.msg("系统错误！");
                    }
                });
            }
            $('#orderTable').bootstrapTable('refresh');
        });
    }

    function attendDay(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        $("#attendModal").modal("show");
        $("#attend").val(data.attend);
        $("#monthId").val(data.id);
    }

    function addAttend(){
        if ($("#attendForm").valid()){
            $.ajax({
                url : '/monthRecord/updateMonth',
                type: 'post',
                dataType : 'json',
                data : {
                    attend : $("#attend").val(),
                    id : $("#monthId").val()
                },
                success : function(result){
                    $("#attendModal").modal("hide");
                    if (result.code == 0){
                        layer.msg("保存成功！");
                    }
                    $('#orderTable').bootstrapTable('refresh');
                },
                error : function(){
                    $('#orderTable').bootstrapTable('refresh');
                    layer.msg("系统错误！");
                }
            });
        }

    }

    /**
     * @desc 站点级别
     */
    function stationRank(index){
        $('#checkboxId').html('');
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        $("#stationRank").modal("show");
        $("#rankRecordId").val(data.id);
        $.ajax({
            url : '/monthRecord/queryStation',
            type: 'post',
            dataType : 'json',
            data : {
                regionId : data.region
            },
            success : function(result){
                if (result.code == 0){
                    var data = result.data;
                    for (var i = 0; i<data.length; i++) {
                        $("#checkboxId").append(' <label  style="margin-right:10px;">' +
                                '<input type="checkbox" name="stationId" value="'+data[i].orderStationId+'" ' +
                                'style="vertical-align: middle;margin-right: 4px;">'+data[i].name+'</label>');
                    }
                }
            },
            error : function(){
                $('#orderTable').bootstrapTable('refresh');
                layer.msg("系统错误！");
            }
        });
    }

    function addRank(){
        var checkVal =[];
        $('input[name="stationId"]:checked').each(function(){
            checkVal.push($(this).val());
        });
        if (checkVal.length == 0){
            layer.msg("请选择站点！");
        }else {
            $.ajax({
                url : '/monthRecord/addStationRank',
                type: 'post',
                dataType : 'json',
                data : {
                    recordId : $("#rankRecordId").val(),
                    level : $("#level").val(),
                    checkVal : checkVal.join(',')
                },
                success : function(result){
                    $("#stationRank").modal("hide");
                    if (result.code == 0){
                        layer.msg("保存成功！");
                    }

                },
                error : function(){
                    $('#orderTable').bootstrapTable('refresh');
                    layer.msg("系统错误！");
                }
            });
        }

    }
</script>