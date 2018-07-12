package com.velvol.salary.controller;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryBjResultDetail;
import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.SalaryResultDetail;
import com.velvol.salary.service.SalaryOrderDetailService;
import com.velvol.salary.service.SalaryResultDetailService;
import com.velvol.salary.util.ExportExcelUtils;
import com.velvol.salary.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
@Controller
@RequestMapping("/resultDetail")
public class SalaryResultDetailController {

    @Autowired
    SalaryResultDetailService salaryResultDetailService;

    @Autowired
    private SalaryOrderDetailService uploadControllerService;

    /**
     * @desc 跳转结果页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/resultDetailPage", method = RequestMethod.GET)
    public String resultDetailPage(Map<String, Object> map, SalaryMonthRecord salaryMonthRecord){
        List<Map<String, Object>> stationList =  uploadControllerService.selectStation(salaryMonthRecord.getRegion());
        map.put("recordId", salaryMonthRecord.getId());
        map.put("year", salaryMonthRecord.getYear());
        map.put("month", salaryMonthRecord.getMonth());
        map.put("stationList", stationList);
        return "/orderform/resultDetail_list";
    }

    /**
     * @desc 北京老骑手结果页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/oldResultDetailPage", method = RequestMethod.GET)
    public String oldResultDetailPage(Map<String, Object> map, SalaryMonthRecord salaryMonthRecord){
        List<Map<String, Object>> stationList =  uploadControllerService.selectStation(salaryMonthRecord.getRegion());
        map.put("recordId", salaryMonthRecord.getId());
        map.put("year", salaryMonthRecord.getYear());
        map.put("month", salaryMonthRecord.getMonth());
        map.put("stationList", stationList);
        return "/orderform/oldResultDetail_list";
    }

    /**
     * @desc 北京新骑手结果页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/newResultDetailPage", method = RequestMethod.GET)
    public String newResultDetailPage(Map<String, Object> map, SalaryMonthRecord salaryMonthRecord){
        List<Map<String, Object>> stationList =  uploadControllerService.selectStation(salaryMonthRecord.getRegion());
        map.put("recordId", salaryMonthRecord.getId());
        map.put("year", salaryMonthRecord.getYear());
        map.put("month", salaryMonthRecord.getMonth());
        map.put("stationList", stationList);
        return "/orderform/newResultDetail_list";
    }

    /**
     * @desc 查询计算结果列表信息
     * @param salaryResultDetail
     * @return
     */
    @RequestMapping(value = "/queryResultDetailPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryResultDetailPage(SalaryResultDetail salaryResultDetail) {
        Map<String, Object> map = new HashMap<>();
        PageInfo pageInfo = salaryResultDetailService.queryResultDetailPage(salaryResultDetail);
        return ResultUtil.pageInfo(pageInfo);
    }

    /**
     * @desc 查询北京计算结果列表信息
     * @param salaryBjResultDetail
     * @return
     */
    @RequestMapping(value = "/queryNewResultDetailPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryNewResultDetailPage(SalaryBjResultDetail salaryBjResultDetail) {
        Map<String, Object> map = new HashMap<>();
        PageInfo pageInfo = salaryResultDetailService.queryNewResultDetailPage(salaryBjResultDetail);
        return ResultUtil.pageInfo(pageInfo);
    }

    /**
     * @desc 查询北京老骑手计算结果列表信息
     * @param salaryBjResultDetail
     * @return
     */
    @RequestMapping(value = "/queryOldResultDetailPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryOldResultDetailPage(SalaryBjResultDetail salaryBjResultDetail) {
        Map<String, Object> map = new HashMap<>();
        PageInfo pageInfo = salaryResultDetailService.queryOldResultDetailPage(salaryBjResultDetail);
        return ResultUtil.pageInfo(pageInfo);
    }


    /**
     * @desc 导出
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportResultDetail", method = RequestMethod.GET)
    public void exportResultDetail(HttpServletResponse response, SalaryResultDetail salaryResultDetail) throws Exception {

        List<Map<String, Object>> orderDetailList = salaryResultDetailService.selectResultDetail(salaryResultDetail);
        //导出文件的标题
        String title = "工资结果列表";
        //设置表格标题行
        String[] headers = new String[]{"序号", "骑手id","姓名","站点","联系方式",
                "入职日期","离职日期","状态","是否自备车","出勤天数","公休","事假","迟到天数","旷工天数","总单量","基本工资",
                "<420单结算","车辆补助","餐补","话补","住宿补助","全勤补助","超出单数","超出单金额","准时率","完成率","满意率",
                "考核不达标扣款","夜间单量","夜间单提成金额", "大夜间单量","大夜单提成金额","单量阶梯提成金额", "100-200元区间单量",
                "订单金额100-200元区间的提成", "大于200单量", "订单金额大于200的提成","大于3公里订单数量","大于3公里订单提成",
                "四五星站点大于30元单量"," 四五星站点(0.3元/0.5元)",
                "补上月的工资","月度冲单奖励","普通超时单量","普通超时扣款","严重超时单量","严重超时扣款",
                "未送达点击送单","罚款","差评1星单","罚款","差评2星单","罚款","投诉单数","罚款","二类投诉单数","罚款",
                "培训费","保险费","车辆使用费","物料使用费","购买物料费用",
                "物料扣款","迟到扣款","旷工扣款","岗位补贴/培训补贴","微笑行动不达标扣款","高温补贴","工资总额","实发薪资","发放状态","备注"
                ,"身份证号","主银行卡号","开户行"};
        String[] names = new String[]{"id", "rider_id","rider_name","station_name","telephone",
                "entry_time","resign_time","state","vehicle","attend_actual","rest","leave","belate","absent","total_number","base_price",
                "no_task_base_price","vehicle_price","meal_price","telephone_price","live_price","present_price","beyond_task_number","over_task_price","on_time_proportion","complete_proportion","pleased_proportion",
                "no_standard_price","night_number","night_price","big_night_number","big_night_price","interval_price","one_two_number",
                "one_two_price","greater_tow_number","greater_two_price","distance_number","distance_price",
                "station_star_num","station_star_price",
                "lastmonth_price","month_punch_price","overtime_adjust_number","cmmon_overtime_price","serious_number","serious_overtime_price",
                "no_service_number","no_service_price","one_star","one_star_price","two_star","two_star_price","complain","complain_price","class_ii_complain","class_ii_complain_price",
                "train_price","insurance","vehicle_deduction_price","equip_use_price","equip_buy_price",
                "equip_price","belate_price","absent_price","subsidy_price","smile_action_price","temperature_price","amount_price","round_amount_price","status","remark"
                ,"ownerid","mainCard","address"};
        //List<Map<String, Object>> stationList =  uploadControllerService.selectStation();
        ExportExcelUtils.exportExcelTwo(title, headers, orderDetailList, names, response);
    }


    /**
     * @desc 导出北京新骑手工资
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportNewResultDetail", method = RequestMethod.GET)
    public void exportNewResultDetail(HttpServletResponse response, SalaryBjResultDetail salaryBjResultDetail) throws Exception {

        List<Map<String, Object>> orderDetailList = salaryResultDetailService.selectBjResultDetail(salaryBjResultDetail);
        //导出文件的标题
        String title = "北京工资结果列表";
        //设置表格标题行
        String[] headers = new String[]{"序号", "骑手id","姓名","职务","站点","联系方式",
                "入职日期","离职日期","状态","是否自备车","出勤天数","公休","迟到天数","旷工天数","内单量","基本工资",
                "<400单结算","充电补助", "车辆补助","餐补","话补","住宿补助","住宿扣款",
                "车辆使用费","完成率≧99%","准时率≧96%","数据考核扣款","夜间单量",
                "夜间单提成金额", "大夜间单量","大夜单提成金额","阶梯提成金额","90≤-<200单量","奖励(2元)",
                "200≤-<400单量","奖励(4元)","400≤-<700单量","奖励(8元)","≥700单量",
                "奖励(12元)","3-4KM单量","奖励(1元)","4-5KM单量","奖励(1.5元)",
                "物料500元（离职第二月返还)","物料扣款","商业险200元","外单单量","外单提成","外单距离补助","外单夜宵补助",
                "午高峰补助","社保扣款","介绍费扣款","组长补贴","皇家骑士","五星上将","一般超时单","罚款（2元/单）","严重超时",
                "罚款（10元/单）","未送达点击送单","罚款（500元/单）","差评1星单","罚款（20元/单）","差评2星单",
                "罚款（10元/单）","投诉单数","罚款（50元/单）","二类投诉单数","罚款（300元/单）","迟到扣款",
                "旷工扣款","工资总额","实发薪资","发放状态","备注","身份证号","主银行卡号","开户行"};
        String[] names = new String[]{"id", "rider_id","rider_name","is_new_rider","station_name","telephone",
                "entry_time","resign_time","state","vehicle","attend_actual","rest","belate","absent","inside_number","base_price",
                "less_task_price","charge_price","vehicle_price","meal_price","telephone_price","live_price","live_Deduct",
                "vehicle_deduction_price","complete_proportion","on_time_proportion","no_standard_price","night_number",
                "night_price","big_night_number","big_night_price","interval_price","interval_two_number","interval_two_price",
                "interval_three_number","interval_three_price","interval_four_number","interval_four_price","interval_five_number",
                "interval_five_price","distance_one_number","diatance_one_price","distance_two_number","distance_two_price",
                "equip_price","user_equip_price","insurance_price","outside_number","outside_price","outside_distance_price","outside_night_price",
                "outside_noon_price","social_security","introduction_fee_deduct","group_leader","royal_knight","five_star_general","overtime_adjust_number","cmmon_overtime_price","serious_number",
                "serious_overtime_price","no_service_number","no_service_price","one_star","one_star_price","two_star",
                "two_star_price","complain","complain_price","class_ii_complain","class_ii_complain_price","belate_price",
                "absent_price","amount_price","round_amount_price","status","remark","ownerid","mainCard","address"};
        //List<Map<String, Object>> stationList =  uploadControllerService.selectStation();
        ExportExcelUtils.exportExcelTwo(title, headers, orderDetailList, names, response);
    }

    /**
     * @desc 导出北京老骑手工资
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportOldResultDetail", method = RequestMethod.GET)
    public void exportOldResultDetail(HttpServletResponse response, SalaryBjResultDetail salaryBjResultDetail) throws Exception {

        List<Map<String, Object>> orderDetailList = salaryResultDetailService.selectBjOldResultDetail(salaryBjResultDetail);
        //导出文件的标题
        String title = "北京工资结果列表";
        //设置表格标题行
        String[] headers = new String[]{"序号", "骑手id","姓名","职务","站点","联系方式",
                "入职日期","离职日期","状态","是否自备车","出勤天数","公休","迟到天数","旷工天数","内单量","基本工资",
                "<400单结算","充电补助", "车辆补助","餐补","话补","住宿补助","住宿扣款",
                "车辆使用费","数据考核扣款","夜间单量",
                "夜间单提成金额", "大夜间单量","大夜单提成金额","阶梯提成金额","80≤-<200单量","奖励(2元)",
                "200≤-<400单量","奖励(5元)","400≤-<700单量","奖励(8元)","≥700单量",
                "奖励(15元)","3-4KM单量","奖励(1元)","4-5KM单量","奖励(2元)",
                "物料500元（离职第二月返还)","物料扣款","商业险200元","外单单量","外单提成","外单距离补助","外单夜宵补助",
                "外单午高峰补助","社保扣款","介绍费扣款","组长补贴","皇家骑士","五星上将","一般超时单","罚款（2元/单）","严重超时",
                "罚款（10元/单）","未送达点击送单","罚款（500元/单）","差评1星单","罚款（20元/单）","差评2星单",
                "罚款（10元/单）","投诉单数","罚款（50元/单）","二类投诉单数","罚款（300元/单）","迟到扣款",
                "旷工扣款","个人所得税","工资总额","实发薪资","发放状态","备注","身份证号","主银行卡号","开户行"};
        String[] names = new String[]{"id", "rider_id","rider_name","is_new_rider","station_name","telephone",
                "entry_time","resign_time","state","vehicle","attend_actual","rest","belate","absent","inside_number","base_price",
                "less_task_price","charge_price","vehicle_price","meal_price","telephone_price","live_price","live_Deduct",
                "vehicle_deduction_price","no_standard_price","night_number",
                "night_price","big_night_number","big_night_price","interval_price","interval_one_number","interval_one_price",
                "interval_three_number","interval_three_price","interval_four_number","interval_four_price","interval_five_number",
                "interval_five_price","distance_one_number","diatance_one_price","distance_two_number","distance_two_price",
                "equip_price","user_equip_price","insurance_price","outside_number","outside_price","outside_distance_price","outside_night_price",
                "outside_noon_price","social_security","introduction_fee_deduct","group_leader","royal_knight","five_star_general","overtime_adjust_number","cmmon_overtime_price","serious_number",
                "serious_overtime_price","no_service_number","no_service_price","one_star","one_star_price","two_star",
                "two_star_price","complain","complain_price","class_ii_complain","class_ii_complain_price","belate_price",
                "absent_price","personal_tax","amount_price","round_amount_price","status","remark","ownerid","mainCard","address"};
        //List<Map<String, Object>> stationList =  uploadControllerService.selectStation();
        ExportExcelUtils.exportExcelTwo(title, headers, orderDetailList, names, response);
    }

    /**
     * @desc 计算工资
     * @param recordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/startCalculate/{recordId}/{regionId}", method = RequestMethod.POST)
    public Object startCalculate(@PathVariable("recordId") Integer recordId, @PathVariable("regionId") Integer regionId){
        salaryResultDetailService.startCalculate(recordId, regionId);
        return ResultUtil.renderSuccess();
    }

    /**
     * @desc 确认操作
     * @param recordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/notarizeOrder/{recordId}", method = RequestMethod.POST)
    public Object notarizeOrder(@PathVariable("recordId") Integer recordId){
        salaryResultDetailService.notarizeOrder(recordId);
        return ResultUtil.renderSuccess();
    }

    /**
     * @desc 修改工资发放状态
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public Object updateStatus(SalaryResultDetail salaryResultDetail, Integer[] ids){

        int count = salaryResultDetailService.updateStatus(salaryResultDetail, ids);
        if (count == ids.length) {
            return ResultUtil.renderSuccess();
        }else {
            return ResultUtil.renderError();
        }
    }

    /**
     * @desc 新增工资人员
     * @param salaryResultDetail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addResultOrder", method = RequestMethod.POST)
    public Object addResultOrder(SalaryResultDetail salaryResultDetail){

        int count = salaryResultDetailService.addResultOrder(salaryResultDetail);
        if (count > 0) {
            return ResultUtil.renderSuccess();
        }else {
            return ResultUtil.renderError();
        }
    }
}
