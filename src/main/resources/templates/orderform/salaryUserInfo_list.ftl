
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>店小二 --信息导入</title>

<#include "../common/css.ftl"/>
    <style>
        .modal-dialog {
            margin: 100px auto;
        }

        .box label{
            text-align: right;
        }
        .form-group{
            margin-bottom: 30px;
        }
    </style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow-y:hidden">
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
        <div class="row J_mainContent" id="content-main" style="overflow-y: scroll">
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>${regionName}考勤导入${year}年${month}月 </h5>

                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                                        <i class="fa fa-wrench"></i>
                                    </a>
                                    <ul class="dropdown-menu dropdown-user">
                                        <li><a href="table_data_tables.html#">选项1</a>
                                        </li>
                                        <li><a href="table_data_tables.html#">选项2</a>
                                        </li>
                                    </ul>
                                    <a class="close-link">
                                        <i class="fa fa-times"></i>
                                    </a>
                                </div>
                            </div>


                            <div class="ibox-content">
                                <div class="main" id="main">
                                    <div>
                                        <div class="row" style="padding-top: 10px;">
                                            <form id="importForm"  enctype="multipart/form-data" >
                                                <div class="col-md-2">
                                                <#--<span>信息导入：</span>-->
                                                    <input type="file" id="file" name="file">
                                                </div>
                                                <button  onclick="submitFile()" type="button" class="btn btn-success">信息导入</button>
                                                <button  onclick="downloadTemplate()" type="button" class="btn btn-success">模版下载</button>
                                                <button type="button" class="btn btn-warning" onclick="cancelPage()">返回</button>
                                            </form>
                                        </div>

                                        <div class="row" style="padding-top: 10px;">
                                            <div class="col-md-2">
                                                <input type="text" class="form-control" id="riderNameTwo" placeholder="姓名">
                                            </div>
                                            <div class="col-md-2">
                                                <select class="form-control" id="stataionId">
                                                    <option value="">站点选择</option>
                                                    <#list stationList as station>
                                                        <option value="${station.orderStationId!}">${station.name}</option>
                                                    </#list>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                                <input type="text" class="form-control" id="riderIdTwo" placeholder="骑手ID">
                                            </div>
                                            <button id="btn-search" type="button" class="btn btn-success" onclick="searchOrder();">查询</button>
                                            <button id="btn-search" type="button" class="btn btn-default" onclick="searchClear();">清空</button>
                                        </div>
                                        <input type="hidden" id="recordId" name="recordId" value="${recordId!}"/>
                                        <table id="orderTable" class="table table-striped table-bordered table-hover dataTables-example" style="font-size: 13px; width: 4000px;">

                                        </table>

                                    </div>

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

<div class="modal fade" id="editUserInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 1000px;">
        <div class="modal-content">
            <form id="userInfoForm">
                <input type="hidden" id="id" name="id"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-center" style="font-size: 2.5rem;">修改各类统计</h4>
                </div>

                <div class="modal-body box" >
                    <div  style="height:500px;overflow-x:hidden; ">
                        <div class="form-group row">
                            <label class="control-label col-sm-2">姓名：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="riderName" name="riderName" readonly/>
                            </div>
                            <label class="control-label col-sm-2">骑手ID：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="riderId" name="riderId" readonly/>
                            </div>
                            <label class="control-label col-sm-2">站点：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="stationName" name="stationName" readonly/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">入职日期：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control datetimepicker"  id="entryTime" name="entryTime" readonly/>
                            </div>
                            <label class="control-label col-sm-2">离职日期：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control datetimepicker"  id="resignTime" name="resignTime" readonly/>
                            </div>
                            <label class="control-label col-sm-2">是否新骑手：</label>
                            <div class="col-sm-2">
                                <select class="form-control"  id="isNewRider" name="isNewRider">
                                    <option value="">请选择</option>
                                    <option value="0">是</option>
                                    <option value="1">否</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">应出勤天数：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="attendShould" name="attendShould"/>
                            </div>
                            <label class="control-label col-sm-2">实际出勤天数：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="attendActual" name="attendActual"/>
                            </div>
                            <label class="control-label col-sm-2">迟到：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="belate" name="belate"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">旷工：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="absent" name="absent"/>
                            </div>
                            <label class="control-label col-sm-2">公休：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="rest" name="rest"/>
                            </div>
                            <label class="control-label col-sm-2">事假：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="leave" name="leave"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">是否自备车：</label>
                            <div class="col-sm-2">
                                <select class="form-control"  id="vehicle" name="vehicle">
                                    <option value="">请选择</option>
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                            <label class="control-label col-sm-2">是否住宿：</label>
                            <div class="col-sm-2">
                                <select class="form-control"  id="live" name="live">
                                    <option value="">请选择</option>
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                            <label class="control-label col-sm-2">岗位补贴/培训补贴：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="subsidyPrice" name="subsidyPrice"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">微笑行动不达标扣款：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="smileActionPrice" name="smileActionPrice"/>
                            </div>
                            <label class="control-label col-sm-2">高温补贴：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="temperaturePrice" name="temperaturePrice"/>
                            </div>
                            <label class="control-label col-sm-2">物料扣款：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="equipPrice" name="equipPrice"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">备注：</label>
                            <div class="col-sm-6">
                                <input type="text"  class="form-control"  id="remark" name="remark"/>
                            </div>


                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-info" onclick="saveUserInfo();">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<#include "../common/js.ftl"/>
</html>
<script>
    var path = '${basePath}';
    //组装查询条件
    var queryParams = function (params) {
        var param = {
            pageNumber: params.pageNumber,
            pageSize: params.pageSize,
            riderName : $("#riderNameTwo").val(),
            stataionId : $("#stataionId").val(),
            riderId : $("#riderIdTwo").val(),
        };
        return param;
    };

    function searchOrder(){
        $('#orderTable').bootstrapTable("refresh");
    }

    function searchClear(){
        $("#riderNameTwo").val("");
        $("#stataionId").val("");
        $("#riderIdTwo").val("");
        $('#orderTable').bootstrapTable("refresh");
    }


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
            url:path+"/salaryUserInfo/querySalaryUserInfoPage?recordId="+$("#recordId").val(),
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
                {field:"riderName",title:"姓名",align:"center",valign:"middle",sortable:"true"},
                {field:"riderId",title:"骑手ID",align:"center",valign:"middle",sortable:"true"},
                {field:"stationName",title:"站点",align:"center",valign:"middle",sortable:"true"},
                {field:"entryTime",title:"入职日期",align:"center",valign:"middle", formatter : dataFormatter},
                {field:"resignTime",title:"离职日期",align:"center",valign:"middle", formatter : dataFormatter},
                {field:"isNewRider",title:"是否新骑手",align:"center",valign:"middle",sortable:"true",
                    formatter : function(value, row, index){
                        if (value == 0) {
                            return "是";
                        }
                        if (value == 1) {
                            return "否";
                        }
                    }},
                //{field:"attendShould",title:"应出勤天数",align:"center",valign:"middle",sortable:"true"},
                {field:"attendActual",title:"实际出勤天数",align:"center",valign:"middle",sortable:"true"},
                {field:"belate",title:"迟到",align:"center",valign:"middle",sortable:"true"},
                {field:"absent",title:"旷工",align:"center",valign:"middle"},
                {field:"rest",title:"公休",align:"center",valign:"middle",sortable:"true"},
                {field:"leave",title:"事假",align:"center",valign:"middle",sortable:"true"},
                {field:"vehicle",title:"是否自备车",align:"center",valign:"middle",sortable:"true",
                    formatter : function(value, row, index){
                        if (value == 0) {
                            return "否";
                        }
                        if (value == 1) {
                            return "是";
                        }
                    }},
                {field:"live",title:"是否住宿",align:"center",valign:"middle",
                    formatter : function(value, row, index){
                        if (value == 0) {
                            return "否";
                        }
                        if (value == 1) {
                            return "是";
                        }
                    }
                },
                {field:"subsidyPrice",title:"岗位补贴/培训补贴",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"smileActionPrice",title:"微笑行动不达标扣款",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"temperaturePrice",title:"高温补贴",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"equipPrice",title:"物料扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"remark",title:"备注",align:"center",valign:"middle",sortable:"true"},
                {field:"operate",title:"操作",align:"center",valign:"middle",width:"250",
                    formatter : function(value, row, index){
                        return  '<button class="btn btn-info btn-xs" data-toggle="modal" href="#editUserInfoModal" onclick="editUserInfo('+index+')">修改</button>';
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
            format: "yyyy-mm-dd",      //格式化日期
            timepicker: false,    //关闭时间选项
            yearStart: 2016,

            autoclose: true,
            yearEnd: 2050,        //设置最大年份
            todayBtn: true,    //关闭选择今天按钮
            startView: 2,
            minView: 2,
            todayHighlight: true
        });

        $("#userInfoForm").validate({
            rules: {
                attendShould : {
                    isNumber : true,
                },
                attendActual : {
                    isNumber : true,
                },
                belate : {//身份证
                    isNumber : true,
                },
                absent : {//手机号
                    isNumber : true,
                },
                rest : {
                    isNumber : true,
                },
                leave : {
                    isNumber : true,
                },
                subsidyPrice : {
                    isNumber : true,
                },
                smileActionPrice : {
                    isNumber : true,
                },
                temperaturePrice : {
                    isNumber : true,
                },
                equipPrice : {
                    isNumber : true,
                }

            },
            messages: {

            }
        });

    });

    function dataFormatter (value, row, index) {
        if (value == null) {
            return "";
        }else {
            return changeDateFormat(value);
        }
    }

    function valueFormatter(value, row, index){
        if (value == undefined && value == null) {
            return "";
        }else {
            return accDiv(value, 100);
        }

    }


    //除法函数，用来得到精确的除法结果
    //说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
    //调用：accDiv(arg1,arg2)
    //返回值：arg1除以arg2的精确结果
    function accDiv(arg1,arg2){
        var t1=0,t2=0,r1,r2;
        try{t1=arg1.toString().split(".")[1].length}catch(e){}
        try{t2=arg2.toString().split(".")[1].length}catch(e){}
        with(Math){
            r1=Number(arg1.toString().replace(".",""));
            r2=Number(arg2.toString().replace(".",""));
            return (r1/r2)*pow(10,t2-t1);
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
        return year+"-"+m+"-"+d;
    }

    /**
     * @desc 信息导入
     * @returns {boolean}
     */
    function submitFile() {
        var file = $("#file").val();
        if(file ==''){
            swal("请选择zip文件！");
            return false;
        }
        var ext = file.slice(file.lastIndexOf(".")+1).toLowerCase();
        if ("zip" != ext) {
            swal("只能上传zip文件");
            return false;
        }else {
            $.ajax({
                url : path+"/salaryUserInfo/batchImport?recordId="+$("#recordId").val(),
                type : "post",
                dataType : "json",
                cache: false,
                data: new FormData($('#importForm')[0]),
                processData: false,
                contentType: false,
                timeout:900000,
                beforeSend:function(){
                    layer.load(0, {shade: [0.5,'#cccc']});
                },
                success : function(result){
                    layer.closeAll('loading');
                    if (result.code == 0) {
                        var html = "<div style='overflow-y:scroll;height: 250px;'>";
                        var data = result.data;
                        for (var i=0; i<data.length;i++) {
                            html += data[i];
                        }
                        swal({
                                    title: "导入提示信息",
                                    text: html+"</div>",
                                    html: true,
                                },
                                function(isConfirm){
                                    if (isConfirm) {
                                        $("#orderTable").bootstrapTable("refresh");
                                    }
                                });
                    }
                    swal(result.msg);
                },
                error : function(){
                    swal("系统异常，请联系管理员");
                }
            });
        }

    }

    function cancelPage() {
        window.location.href = path+"/monthRecord/queryMonthRecordPage";
    }

    function downloadTemplate(){
        window.location.href = path+"/salaryUserInfo/downloadTemplate?flag=" + 1;
    }

    function editUserInfo(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        $("#id").val(data.id);
        $("#riderName").val(data.riderName);
        $("#riderId").val(data.riderId);
        $("#stationName").val(data.stationName);
        $("#entryTime").val(data.entryTime);
        $("#resignTime").val(data.resignTime);
        $("#isNewRider").val(data.isNewRider);
        $("#attendShould").val(data.attendShould);
        $("#attendActual").val(data.attendActual);
        $("#belate").val(data.belate);
        $("#absent").val(data.absent);
        $("#rest").val(data.rest);
        $("#leave").val(data.leave);
        $("#vehicle").val(data.vehicle);
        $("#live").val(data.live);
        if (data.subsidyPrice != null) {
            $("#subsidyPrice").val(accDiv(data.subsidyPrice, 100));
        }
        if (data.smileActionPrice != null) {
            $("#smileActionPrice").val(accDiv(data.smileActionPrice, 100));
        }
        if (data.temperaturePrice != null) {
            $("#temperaturePrice").val(accDiv(data.temperaturePrice, 100));
        }
        if (data.equipPrice != null) {
            $("#equipPrice").val(accDiv(data.equipPrice, 100));
        }
        $("#remark").val(data.remark);
    }

    function saveUserInfo(){
        if (!$("#userInfoForm").valid()) {
            return;
        }else {
            $.ajax({
                url : '/salaryUserInfo/updateUserInfo',
                type: 'post',
                dataType : 'json',
                data : $("#userInfoForm").serialize(),
                success : function(result){
                    $("#editUserInfoModal").modal("hide");
                    $('#orderTable').bootstrapTable('refresh');
                    if (result.code == 0){
                        layer.msg("保存成功！");
                    }else {
                        layer.msg("保存失败！");
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