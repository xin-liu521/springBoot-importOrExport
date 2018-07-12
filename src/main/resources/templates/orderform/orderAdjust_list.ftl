
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
        <div class="row J_mainContent" id="content-main" style="overflow-y:scroll">
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>${regionName}各类统计导入${year}年${month}月 </h5>

                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                                        <i class="fa fa-wrench"></i>
                                    </a>
                                    <ul class="dropdown-menu dropdown-user">
                                        <li><a href="#">选项1</a>
                                        </li>
                                        <li><a href="#">选项2</a>
                                        </li>
                                    </ul>
                                    <a class="close-link">
                                        <i class="fa fa-times"></i>
                                    </a>
                                </div>
                            </div>


                            <div class="ibox-content">
                                <div class="main" id="main">


                                    <div class="table-responsive" style="overflow-x:hidden;">
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

<div class="modal fade" id="editAdjustModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 1000px;">
        <div class="modal-content">
            <form id="editAdjustForm">
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
                            <label class="control-label col-sm-2">人效单量：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="amount" name="amount"/>
                            </div>
                            <label class="control-label col-sm-2">普通超时容忍单量：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="overtimeAdjustNumber" name="overtimeAdjustNumber"/>
                            </div>
                            <label class="control-label col-sm-2">严重超时容忍单量：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="seriousOvertimeNumber" name="seriousOvertimeNumber"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">准时率：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="onTimeProportion" name="onTimeProportion"/>
                            </div>
                            <label class="control-label col-sm-2">完成率：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="completeProportion" name="completeProportion"/>
                            </div>
                            <label class="control-label col-sm-2">满意率：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="pleasedProportion" name="pleasedProportion"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">未送达点击送达单量：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="noServiceNumber" name="noServiceNumber"/>
                            </div>
                            <label class="control-label col-sm-2">1星单：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="oneStar" name="oneStar"/>
                            </div>
                            <label class="control-label col-sm-2">2星单：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="twoStar" name="twoStar"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">投诉单：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="complain" name="complain"/>
                            </div>
                            <label class="control-label col-sm-2">二类投诉单：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="classIiComplain" name="classIiComplain"/>
                            </div>
                            <label class="control-label col-sm-2">社保扣款：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="socialSecurity" name="socialSecurity"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">住宿扣款：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="liveDeduct" name="liveDeduct"/>
                            </div>
                            <label class="control-label col-sm-2">介绍费扣款：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="introductionFeeDeduct" name="introductionFeeDeduct"/>
                            </div>
                            <label class="control-label col-sm-2">组长补贴：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="groupLeader" name="groupLeader"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2">皇家骑士：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="royalKnight" name="royalKnight"/>
                            </div>
                            <label class="control-label col-sm-2">五星上将：</label>
                            <div class="col-sm-2">
                                <input type="text"  class="form-control"  id="fiveStarGeneral" name="fiveStarGeneral"/>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-info" onclick="saveAdjust();">保存</button>
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
            url:path+"/orderAdjust/queryOrderAdjustPage?recordId="+$("#recordId").val(),
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
                //{field:"systemNumber",title:"系统统计数量",align:"center",valign:"middle",sortable:"true"},
                {field:"amount",title:"人效单量",align:"center",valign:"middle",sortable:"true"},
                //{field:"commonOvertimeNumber",title:"普通超时订单数量",align:"center",valign:"middle",sortable:"true"},
                {field:"overtimeAdjustNumber",title:"普通超时容忍后单量",align:"center",valign:"middle",sortable:"true"},
                {field:"seriousOvertimeNumber",title:"严重超时容忍后单量",align:"center",valign:"middle",sortable:"true"},
                {field:"onTimeProportion",title:"准时率",align:"center",valign:"middle"},
                {field:"completeProportion",title:"完成率",align:"center",valign:"middle",sortable:"true"},
                {field:"pleasedProportion",title:"满意率",align:"center",valign:"middle",sortable:"true"},
                {field:"noServiceNumber",title:"未送达点击送达单量",align:"center",valign:"middle",sortable:"true"},
                {field:"oneStar",title:"1星单",align:"center",valign:"middle",sortable:"true"},
                {field:"twoStar",title:"2星单",align:"center",valign:"middle",sortable:"true"},
                {field:"complain",title:"投诉单",align:"center",valign:"middle",sortable:"true"},
                {field:"classIiComplain",title:"二类投诉单",align:"center",valign:"middle",sortable:"true"},
                {field:"socialSecurity",title:"社保扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"liveDeduct",title:"住宿扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"introductionFeeDeduct",title:"介绍费扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"groupLeader",title:"组长补贴",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"royalKnight",title:"皇家骑士",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"fiveStarGeneral",title:"五星上将",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"operate",title:"操作",align:"center",valign:"middle",
                    formatter : function(value, row, index){
                        return '<button class="btn btn-info btn-xs"  data-toggle="modal" href="#editAdjustModal" onclick="editAdjust('+index+')">修改</button> ';
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

        $("#editAdjustForm").validate({
            rules: {
                amount : {//姓名
                    isNumber : true,
                },
                overtimeAdjustNumber : {
                    isNumber : true,
                },
                seriousOvertimeNumber : {
                    isNumber : true,
                },
                onTimeProportion : {
                    isPercent : true,
                },
                completeProportion : {
                    isPercent : true,
                },
                pleasedProportion : {
                    isPercent : true,
                },
                noServiceNumber : {
                    isNumber : true,
                },
                oneStar : {
                    isNumber : true,
                },
                twoStar : {
                    isNumber : true,
                },
                complain : {
                    isNumber : true,
                },
                classIiComplain : {
                    isNumber : true,
                },
                socialSecurity : {
                    isNumber : true,
                },
                liveDeduct : {
                    isNumber : true,
                },
                introductionFeeDeduct : {
                    isNumber : true,
                },
                groupLeader : {
                    isNumber : true,
                },
                royalKnight : {
                    isNumber : true,
                },
                fiveStarGeneral : {
                    isNumber : true,
                }

            },
            messages: {

            }
        });

    });

    function numberFor(value, row, index){
        if (value == null || value == undefined) {
            return "";
        }else {
            return parseFloat(accMul(value, 100).toFixed(2))+"%";
        }

    }

    //返回值：arg1乘以arg2的精确结果
    function accMul(arg1,arg2)
    {
        var m=0,s1=arg1.toString(),s2=arg2.toString();
        try{m+=s1.split(".")[1].length}catch(e){}
        try{m+=s2.split(".")[1].length}catch(e){}
        return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
    }

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
        return year+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
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
                url : path+"/orderAdjust/batchImport?recordId="+$("#recordId").val(),
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
                        var html = "";
                        var data = result.data;
                        for (var i=0; i<data.length;i++) {
                            html += data[i];
                        }
                        swal({
                                    title: "导入提示信息",
                                    text: html,
                                    html: true
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
        window.location.href = path+"/salaryUserInfo/downloadTemplate?flag=" + 0;
    }

    function editAdjust(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        $("#id").val(data.id);
        $("#riderName").val(data.riderName);
        $("#riderId").val(data.riderId);
        $("#stationName").val(data.stationName);
        $("#amount").val(data.amount);
        $("#overtimeAdjustNumber").val(data.overtimeAdjustNumber);
        $("#seriousOvertimeNumber").val(data.seriousOvertimeNumber);
        $("#onTimeProportion").val(data.onTimeProportion);
        $("#completeProportion").val(data.completeProportion);
        $("#pleasedProportion").val(data.pleasedProportion);
        $("#noServiceNumber").val(data.noServiceNumber);
        $("#oneStar").val(data.oneStar);
        $("#twoStar").val(data.twoStar);
        $("#complain").val(data.complain);
        $("#classIiComplain").val(data.classIiComplain);
        if (data.socialSecurity != null) {
            $("#socialSecurity").val(accDiv(data.socialSecurity, 100));
        }
        if (data.liveDeduct != null) {
            $("#liveDeduct").val(accDiv(data.liveDeduct, 100));
        }
        if (data.introductionFeeDeduct != null) {
            $("#introductionFeeDeduct").val(accDiv(data.introductionFeeDeduct, 100));
        }
        if (data.groupLeader != null) {
            $("#groupLeader").val(accDiv(data.groupLeader, 100));
        }
        if (data.royalKnight != null) {
            $("#royalKnight").val(accDiv(data.royalKnight, 100));
        }
        if (data.fiveStarGeneral != null) {
            $("#fiveStarGeneral").val(accDiv(data.fiveStarGeneral, 100));
        }
    }

    function saveAdjust(){
        if( !$("#editAdjustForm").valid()){
            return;
        }else {
            $.ajax({
                url : '/orderAdjust/updateOrderAdjust',
                type: 'post',
                dataType : 'json',
                data : $("#editAdjustForm").serialize(),
                success : function(result){
                    $("#editAdjustModal").modal("hide");
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