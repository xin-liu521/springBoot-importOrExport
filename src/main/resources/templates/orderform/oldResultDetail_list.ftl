
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>店小二 --工资计算结果</title>

<#include "../common/css.ftl"/>
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
                                <h5>北京老骑手工资结果${year!}年${month!}月 </h5>
                            </div>


                            <div class="ibox-content">
                                <div class="main" id="main">
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col-md-6">
                                        <#--<button type="button" class="btn btn-success" data-toggle="modal" href="#addModal">新增</button>-->
                                            <button type="button" class="btn btn-success" onclick="editStatusModal()">批量修改发放状态</button>
                                            <button type="button" class="btn btn-success" onclick="exportFile()">导出列表</button>
                                            <button type="button" class="btn btn-warning" onclick="cancelPage()">返回</button>
                                        </div>
                                    </div>
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col-md-2">
                                            <input type="text" class="form-control" id="riderName" placeholder="姓名">
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
                                            <input type="text" class="form-control" id="riderId" placeholder="骑手ID">
                                        </div>
                                        <div class="col-md-2">
                                            <select class="form-control" id="status">
                                                <option value="">发放状态选择</option>
                                                <option value="0">发放</option>
                                                <option value="1">暂扣</option>
                                                <option value="2">不发放</option>
                                            </select>
                                        </div>
                                        <button id="btn-search" type="button" class="btn btn-success" onclick="searchOrder();">查询</button>
                                        <button id="btn-search" type="button" class="btn btn-default" onclick="searchClear();">清空</button>
                                    </div>
                                    <input type="hidden" id="recordId" name="recordId" value="${recordId!}"/>
                                    <table id="orderTable" class="table table-striped table-bordered table-hover dataTables-example" style="font-size: 13px; width: 8500px;">

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
<div class="modal fade" id="addPageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 480px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title text-center" style="font-size: 2.5rem;">修改状态</h4>
            </div>
            <div class="modal-body">
                <select class="form-control" id="statusOne" name="status">
                    <option value= 0>发放</option>
                    <option value="1">暂扣</option>
                    <option value="2">不发放</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-info" onclick="updateStatus();">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editStatusModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 480px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title text-center" style="font-size: 2.5rem;">修改状态</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id" name="id"/>
                <select class="form-control" id="statusTwo" name="status">
                    <option value= 0>发放</option>
                    <option value="1">暂扣</option>
                    <option value="2">不发放</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-info" onclick="batchStatus();">保存</button>
            </div>
        </div>
    </div>
</div>

</body>
<#include "../common/js.ftl"/>
</html>
<script>
    toastr.options.positionClass = 'toast-top-center';
    var path = '${basePath}';
    //组装查询条件
    var queryParams = function (params) {
        var param = {
            pageNumber: params.pageNumber,
            pageSize: params.pageSize,
            riderName : $("#riderName").val(),
            stataionId : $("#stataionId").val(),
            riderId : $("#riderId").val(),
            status : $("#status").val(),
        };
        return param;
    };

    function searchOrder(){
        $('#orderTable').bootstrapTable("refresh");
    }

    function searchClear(){
        $("#riderName").val("");
        $("#stataionId").val("");
        $("#riderId").val("");
        $("#status").val("");
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
            url:path+"/resultDetail/queryOldResultDetailPage?recordId="+$("#recordId").val(),
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
                {field:"rider_id",title:"骑手ID",align:"center",valign:"middle",sortable:"true"},
                {field:"rider_name",title:"姓名",align:"center",valign:"middle",sortable:"true"},
                {field:"is_new_rider",title:"职务",align:"center",valign:"middle",  formatter :
                        function(value, row, index){
                            if (value == 0){
                                return "新骑手";
                            }else{
                                return "老骑手";
                            }
                        }
                },
                {field:"station_name",title:"站点",align:"center",valign:"middle",sortable:"true"},
                {field:"telephone",title:"联系方式",align:"center",valign:"middle",sortable:"true"},
                {field:"entry_time",title:"入职日期",align:"center",valign:"middle", formatter : dataFormatter},
                {field:"resign_time",title:"离职日期",align:"center",valign:"middle", formatter : dataFormatter},
                {field:"state",title:"骑手状态",align:"center",valign:"middle",  formatter :
                        function(value, row, index){
                            if (row.resign_time != null){
                                return "离职";
                            }else{
                                return "在职";
                            }
                        }
                },
                {field:"vehicle",title:"是否自备车",align:"center",valign:"middle",  formatter :
                        function(value, row, index){
                            if (value == 1){
                                return "是";
                            }else if (value == 0){
                                return "否";
                            }else {
                                return "";
                            }
                        }
                },
                {field:"attend_actual",title:"出勤天数",align:"center",valign:"middle"},
                {field:"rest",title:"公休",align:"center",valign:"middle"},
//                {field:"leave",title:"事假",align:"center",valign:"middle"},
                {field:"belate",title:"迟到天数",align:"center",valign:"middle"},
                {field:"absent",title:"旷工天数",align:"center",valign:"middle"},
                {field:"inside_number",title:"内单数量",align:"center",valign:"middle",sortable:"true"},
                {field:"base_price",title:"基本工资",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"less_task_price",title:"<400薪资",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"charge_price",title:"充电补助",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"vehicle_price",title:"车辆补助",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"meal_price",title:"餐补",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"telephone_price",title:"话补",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"live_price",title:"住宿补助",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"live_Deduct",title:"住宿扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"vehicle_deduction_price",title:"车辆使用费",align:"center",valign:"middle", formatter : valueFormatter},
//                {field:"complete_proportion",title:"完成率≧99%",align:"center",valign:"middle",sortable:"true", formatter : numberFor},
//                {field:"on_time_proportion",title:"准时率≧96%",align:"center",valign:"middle", formatter : numberFor},
//                {field:"deliver_score",title:"配送评分",align:"center",valign:"middle"},
                {field:"no_standard_price",title:"数据考核扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"night_number",title:"21:00-00:00单量",align:"center",valign:"middle",sortable:"true"},
                {field:"night_price",title:"补贴（2/单）",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"big_night_number",title:"00:00-3:00单量",align:"center",valign:"middle",sortable:"true"},
                {field:"big_night_price",title:"补贴（3元/单）",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"interval_price",title:"阶梯提成",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"interval_one_number",title:"80≤-<200单量",align:"center",valign:"middle"},
                {field:"interval_one_price",title:"奖励(2元)",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"interval_three_number",title:"200≤-<400单量",align:"center",valign:"middle",sortable:"true"},
                {field:"interval_three_price",title:"奖励(5元)",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"interval_four_number",title:"400≤-<700单量",align:"center",valign:"middle",sortable:"true"},
                {field:"interval_four_price",title:"奖励(8元)",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"interval_five_number",title:"≥700单量",align:"center",valign:"middle",sortable:"true"},
                {field:"interval_five_price",title:"奖励(15元)",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"distance_one_number",title:"3-4KM单量",align:"center",valign:"middle"},
                {field:"diatance_one_price",title:"奖励(1元)",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"distance_two_number",title:"4-5KM单量",align:"center",valign:"middle",sortable:"true"},
                {field:"distance_two_price",title:"奖励(2元)",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"equip_price",title:"物料500元（离职第二月返还)",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"user_equip_price",title:"物料扣款",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"insurance_price",title:"商业险200元",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"outside_number",title:"外单单量",align:"center",valign:"middle",sortable:"true"},
                {field:"outside_price",title:"外单提成",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"outside_distance_price",title:"外单距离补助",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"outside_night_price",title:"外单夜宵补助",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"outside_noon_price",title:"外单午高峰补助",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"social_security",title:"社保扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"introduction_fee_deduct",title:"介绍费扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"group_leader",title:"组长补贴",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"royal_knight",title:"皇家骑士",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"five_star_general",title:"五星上将",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"overtime_adjust_number",title:"一般超时单",align:"center",valign:"middle",sortable:"true"},
                {field:"cmmon_overtime_price",title:"罚款（2元/单）",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"serious_number",title:"严重超时",align:"center",valign:"middle",sortable:"true"},
                {field:"serious_overtime_price",title:"罚款（10元/单）",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"no_service_number",title:"未送达点击送单",align:"center",valign:"middle",sortable:"true"},
                {field:"no_service_price",title:"罚款（500元/单）",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"one_star",title:"差评1星单",align:"center",valign:"middle"},
                {field:"one_star_price",title:"罚款（20元/单）",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"two_star",title:"差评2星单",align:"center",valign:"middle"},
                {field:"two_star_price",title:"罚款（10元/单）",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"complain",title:"投诉单数",align:"center",valign:"middle"},
                {field:"complain_price",title:"罚款（50元/单）",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"class_ii_complain",title:"二类投诉单数",align:"center",valign:"middle"},
                {field:"class_ii_complain_price",title:"罚款（300元/单）",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"belate_price",title:"迟到扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"absent_price",title:"旷工扣款",align:"center",valign:"middle",sortable:"true", formatter : valueFormatter},
                {field:"personal_tax",title:"个人所得税",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"amount_price",title:"工资总额",align:"center",valign:"middle", formatter : valueFormatter},
                {field:"round_amount_price",title:"实发薪资",align:"center",valign:"middle"},
                {field:"status",title:"发放状态",align:"center",valign:"middle",  formatter :
                        function(value, row, index){
                            if (value == 0){
                                return "发放";
                            }else if (value == 1){
                                return "暂扣";
                            }else if (value == 2){
                                return "不发放";
                            }else {
                                return "";
                            }
                        }
                },
                {field:"remark",title:"备注",align:"center",valign:"middle"},
                {field:"ownerid",title:"身份证号",align:"center",valign:"middle",sortable:"true"},
                {field:"mainCard",title:"主银行卡号",align:"center",valign:"middle",sortable:"true"},
                {field:"address",title:"开户行",align:"center",valign:"middle",sortable:"true"},
//                {field:"addtionalCard",title:"副银行卡号",align:"center",valign:"middle",sortable:"true"},
//                {field:"addressTwo",title:"开户行",align:"center",valign:"middle",sortable:"true"},
                {field:"operate",title:"操作",align:"center",valign:"middle",width:"250",formatter :
                        function(value, row, index){
                            return ' <button class="btn btn-info btn-xs" data-toggle="modal" href="#addPageModal" onclick="statusModal('+index+')">修改发放状态</button>';
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


    });

    //初始化时间
    function dataFormatter (value, row, index) {
        if (value == null) {
            return "";
        }else {
            return changeDateFormat(value);
        }
    }

    //初始化金额
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

    //初始化百分比
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
     * @desc 导出
     */
    function exportFile(){
        var riderName = $("#riderName").val();
        var stataionId = $("#stataionId").val();
        var riderId = $("#riderId").val();
        window.location.href = path+"/resultDetail/exportOldResultDetail?recordId="+$("#recordId").val()+"&riderName="+riderName+"&stataionId="+
                stataionId+"&riderId="+riderId;
    }

    function cancelPage() {
        window.location.href = path+"/monthRecord/queryMonthRecordPage";
    }

    //修改状态
    function statusModal(index){
        var datas = $table.bootstrapTable('getData');
        var data=datas[index];//获取这行数据
        $("#id").val(data.id);
        $("#statusOne").val(data.status);
    }

    function updateStatus(){
        var ids = [];
        ids.push($("#id").val());

        $.ajax({
            url : path+"/resultDetail/updateStatus",
            type : "post",
            dataType : "json",
            data : {
                ids :  ids.join(","),
                status : $("#statusOne").val()
            },
            success : function(result){
                $("#addPageModal").modal("hide");
                if (result.code == 0) {
                    Command: toastr["success"]("修改成功");
                }else{
                    Command: toastr["error"]("修改失败");
                }
                $('#orderTable').bootstrapTable('refresh');
            },
            error : function(){
                $("#addPageModal").modal("hide");
                Command: toastr["error"]("操作异常，请刷新页面后再次尝试");
            }
        });
    }

    //批量修改
    function editStatusModal(){
        var result = $('#orderTable').bootstrapTable('getSelections');
        var ids = [];
        for (var i = 0; i < result.length; i++) {
            var item = result[i];
            ids.push(item.id);
        }
        if (ids.length == 0) {
            Command: toastr["warning"]("请选择需要操作的数据");
        }else {
            $("#editStatusModal").modal("show");
        }
    }


    function batchStatus(){
        var result = $('#orderTable').bootstrapTable('getSelections');
        var ids = [];
        for (var i = 0; i < result.length; i++) {
            var item = result[i];
            ids.push(item.id);
        }
        if(ids.length > 0) {
            $.ajax({
                url: path+"/resultDetail/updateStatus",
                dataType: "json",
                data: {
                    "ids": ids.join(','),
                    status : $("#statusTwo").val()
                },
                type: "post",
                success: function (result) {
                    $("#editStatusModal").modal("hide");
                    if (result.code == 0) {
                        Command: toastr["success"]("修改成功");
                    }else{
                        Command: toastr["error"]("修改失败");
                    }
                    $('#orderTable').bootstrapTable('refresh');
                },
                error: function (XMLResponse) {
                    $("#editStatusModal").modal("hide");
                    Command: toastr["error"]("操作异常，请刷新页面后再次尝试");
                }
            });

        }else {
            Command: toastr["warning"]("请选择需要操作的数据");
        }
    }

    //新增
    function addOrder(){
        var amountPrice = $("#amountPrice").val();

        $.ajax({
            url: path+"/resultDetail/addResultOrder",
            dataType: "json",
            data: {
                recordId : $("#recordId").val(),
                riderName: $("#riderNameOne").val(),
                stataionId : $("#stataionIdOne").val(),
                stationName : $("#stataionIdOne").find("option:selected").text(),
                riderId : $("#riderIdOne").val(),
                amountPrice : accMul(amountPrice, 100),
            },
            type: "post",
            success: function (result) {
                $("#addModal").modal("hide");
                if (result.code == 0) {
                    Command: toastr["success"]("修改成功");
                }else{
                    Command: toastr["error"]("修改失败");
                }
                $('#orderTable').bootstrapTable('refresh');
            },
            error: function (XMLResponse) {
                $("#addModal").modal("hide");
                Command: toastr["error"]("操作异常，请刷新页面后再次尝试");
            }
        });
    }
</script>