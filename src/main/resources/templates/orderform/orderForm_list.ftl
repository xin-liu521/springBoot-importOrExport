
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>店小二 --信息导入</title>

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
        <div class="row " id="content-main"  style="overflow-y:scroll">
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>${regionName}订单导入${year}年${month}月 </h5>

                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
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


                            <div class="ibox-content table-responsive">
                                <div class="main" id="main">
                                    <div class="row" style="padding-top: 10px;">
                                        <form id="importForm"  enctype="multipart/form-data" >
                                            <div class="col-md-2">
                                                <#--<span>信息导入：</span>-->
                                                <input type="file" id="file" name="file">
                                            </div>
                                            <button  onclick="submitFile()" type="button" id="buttonId" class="btn btn-success">信息导入</button>
                                            <#--<button type="button" class="btn btn-success" onclick="exportFile()">导出列表</button>-->
                                            <button type="button" class="btn btn-warning" onclick="cancelPage()">返回</button>
                                        </form>
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
                                            <select class="form-control" id="overtime">
                                                <option value="">超时单选择</option>
                                                <option value="1">普通超时</option>
                                                <option value="0">严重超时</option>
                                            </select>
                                        </div>
                                        <button id="btn-search" type="button" class="btn btn-success" onclick="searchOrder();">查询</button>
                                        <button id="btn-search" type="button" class="btn btn-default" onclick="searchClear();">清空</button>
                                    </div>
                                    <input type="hidden" id="recordId" name="recordId" value="${recordId!}"/>
                                    <table id="orderTable" class="table table-striped table-bordered table-hover dataTables-example" style="font-size: 13px; width: 5200px;">

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
            riderName : $("#riderName").val(),
            stataionId : $("#stataionId").val(),
            riderId : $("#riderId").val(),
            overtime : $("#overtime").val()
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
        $("#overtime").val("");
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
            url:path+"/salaryOrder/queryOrderDetailPage?recordId="+$("#recordId").val(),
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
                {field:"orderId",title:"订单号",align:"center",valign:"middle",sortable:"true"},
                {field:"waybillId",title:"运单号",align:"center",valign:"middle",sortable:"true"},
                {field:"sellerNum",title:"流水号",align:"center",valign:"middle"},
                {field:"sellerName",title:"商家名称",align:"center",valign:"middle",sortable:"true"},
                {field:"sellerId",title:"商家ID",align:"center",valign:"middle",sortable:"true"},
                {field:"city",title:"城市",align:"center",valign:"middle"},
                {field:"riderName",title:"骑手",align:"center",valign:"middle",sortable:"true"},
                {field:"riderId",title:"骑手ID",align:"center",valign:"middle",sortable:"true"},
                {field:"stationName",title:"站点",align:"center",valign:"middle"},
                {field:"stataionId",title:"站点ID",align:"center",valign:"middle",sortable:"true"},
                {field:"tradeArea",title:"商圈",align:"center",valign:"middle",sortable:"true"},
                {field:"preorder",title:"预订单",align:"center",valign:"middle"},
                {field:"status",title:"状态",align:"center",valign:"middle",sortable:"true"},
                {field:"organizationType",title:"组织类型",align:"center",valign:"middle",sortable:"true"},
                {field:"epibolyType",title:"众包类型",align:"center",valign:"middle"},
                {field:"stataionId",title:"站点ID",align:"center",valign:"middle",sortable:"true"},
                {field:"deliveryDuration",title:"配送时效",align:"center",valign:"middle",sortable:"true"},
                {field:"waitDuration",title:"等待时长",align:"center",valign:"middle",sortable:"true"},
                {field:"arriveDuration",title:"送达时间",align:"center",valign:"middle"},
                {field:"hitDuration",title:"连击时长",align:"center",valign:"middle",sortable:"true"},
                {field:"navDistance",title:"导航距离",align:"center",valign:"middle",sortable:"true"},
                {field:"lineDistance",title:"折线距离",align:"center",valign:"middle"},
                {field:"sellerAddress",title:"商家地址",align:"center",valign:"middle",sortable:"true"},
                {field:"sellerDeliveryScore",title:"商家配送评分",align:"center",valign:"middle",sortable:"true"},
                {field:"sellerDeliveryApprise",title:"商家配送评价",align:"center",valign:"middle"},
                {field:"orderOriginalPrice",title:"订单原价",align:"center",valign:"middle",sortable:"true"},
                {field:"orderPrice",title:"订单金额",align:"center",valign:"middle",sortable:"true"},
                {field:"discountPrice",title:"折扣金额",align:"center",valign:"middle"},
                {field:"paySeller",title:"付商家款",align:"center",valign:"middle"},
                {field:"actualPay",title:"实际付款",align:"center",valign:"middle",sortable:"true"},
                {field:"userPay",title:"收用户款",align:"center",valign:"middle",sortable:"true"},
                {field:"actualGather",title:"实际收款",align:"center",valign:"middle"},
                {field:"deliveryPrice",title:"配送费",align:"center",valign:"middle",sortable:"true"},
                {field:"orderTime",title:"下单时间",align:"center",valign:"middle",formatter: dataFormatter},
                {field:"payTime",title:"支付时间",align:"center",valign:"middle",sortable:"true",formatter: dataFormatter},
                {field:"hopeTime",title:"期望送达时间",align:"center",valign:"middle",sortable:"true",formatter: dataFormatter},
                {field:"sellerPushTime",title:"商家推单时间",align:"center",valign:"middle",formatter: dataFormatter},
                {field:"dispatchTime",title:"调度时间",align:"center",valign:"middle",formatter: dataFormatter},
                {field:"receiveTime",title:"接单时间",align:"center",valign:"middle",sortable:"true",formatter: dataFormatter},
                {field:"arriveShopTime",title:"到店时间",align:"center",valign:"middle",sortable:"true",formatter: dataFormatter},
                {field:"pickupTime",title:"取货时间",align:"center",valign:"middle",formatter: dataFormatter},
                {field:"arriveTime",title:"送达时间",align:"center",valign:"middle",formatter: dataFormatter},
                {field:"cancelTime",title:"取消事件",align:"center",valign:"middle",sortable:"true"},
                {field:"cancelReason",title:"取消原因",align:"center",valign:"middle",sortable:"true"},
                {field:"cancelOperator",title:"取消操作人",align:"center",valign:"middle"},
                {field:"applyRefundReason",title:"申请退款原因",align:"center",valign:"middle"},
                {field:"applyRefundOperator",title:"申请退款操作人",align:"center",valign:"middle",sortable:"true"},

                {field:"operate",title:"操作",align:"center",valign:"middle",width:"250"}
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
            $("#buttonId").attr("disabled", "disabled");
            $.ajax({
                url : path+"/salaryOrder/batchImport?recordId="+$("#recordId").val(),
                type : "post",
                dataType : "json",
                cache: false,
                data: new FormData($('#importForm')[0]),
                processData: false,
                contentType: false,
                timeout:1500000,
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
                                $("#buttonId").attr("disabled", false);
                            }
                        });
                    }
                    if (result.code == -1) {
                        swal("系统异常！");
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    layer.closeAll('loading');
                    if(textStatus == 'timeout'){//超时,status还有success,error等值的情况
                        swal("请求超时！");
                    }
                    $("#buttonId").attr("disabled", false);
                    $("#orderTable").bootstrapTable("refresh");
                }
            });
        }

    }

    /**
     * @desc 导出
     */
    function exportFile(){
        window.location.href = path+"/salaryOrder/exportOrderDetail?recordId="+$("#recordId").val();
    }

    function cancelPage() {
        window.location.href = path+"/monthRecord/queryMonthRecordPage";
    }
</script>