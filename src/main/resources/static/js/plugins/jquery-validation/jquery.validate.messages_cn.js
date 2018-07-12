/*
 * Translated default messages for the jQuery validation plugin.
 * Language: CN
 * Author: Fayland Lam <fayland at gmail dot com>
 */
jQuery.extend(jQuery.validator.messages, {
        required: "<span style='color: red'>该属性不能为空</span>",
		remote: "<span style='color: red'>该字段已存在</span>",
		email: "<span style='color: red'>请输入正确格式的Email地址</span>",
		url: "<span style='color: red'>请输入合法的网址</span>",
		date: "<span style='color: red'>请输入合法的日期</span>",
		dateISO: "<span style='color: red'>请输入合法的日期 (ISO).</span>",
		number: "<span style='color: red'>请输入合法的数字</span>",
		digits: "<span style='color: red'>只能输入整数</span>",
		creditcard: "<span style='color: red'>请输入合法的信用卡号</span>",
		equalTo: "<span style='color: red'>请再次输入相同的值</span>",
		accept: "<span style='color: red'>请输入拥有合法后缀名的字符串</span>",
		maxlength: jQuery.validator.format("<span style='color: red'>请输入一个长度最多是 {0} 的字符串</span>"),
		minlength: jQuery.validator.format("<span style='color: red'>请输入一个长度最少是 {0} 的字符串</span>"),
		rangelength: jQuery.validator.format("<span style='color: red'>请输入一个长度介于 {0} 和 {1} 之间的字符串</span>"),
		range: jQuery.validator.format("<span style='color: red'>请输入一个介于 {0} 和 {1} 之间的值</span>"),
		max: jQuery.validator.format("<span style='color: red'>请输入一个最大为 {0} 的值</span>"),
		min: jQuery.validator.format("<span style='color: red'>请输入一个最小为 {0} 的值</span>"),
		isMobile:"<span style='color: red'>请输入正确的联系人手机号</span>",
	    isTel:"<span style='color: red'>请输入正确的公司电话</span>",
    	isNumber:"<span style='color: red'>请输入数字</span>",
	    isPercent:"<span style='color: red'>请输入百分数</span>",
		selectNone : "<span style='color: red'>必须选择一项</span>",
	bankCode:"<span style='color: red'>请输入正确的银行账号</span>"
});