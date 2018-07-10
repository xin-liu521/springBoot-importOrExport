package com.velvol.salary.util.wx;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: MaterialTypes
 * @Description: 素材类型枚举类
 * @version V1.0
 */
public class MaterialTypes {

	/**
	 * 图片
	 */
	public final static String IMAGE = "image";
	
	/**
	 * 视频
	 */
	public final static String VIDEO = "video";
	
	/**
	 * 语音
	 */
	public final static String VOICE = "voice";
	
	/**
	 * 图文
	 */
	public final static String NEWS = "news";

	public static final String APP_ID = "wx34957e96730f18dd";//根据自己的替换

	public static final String APP_SECRET = "2ddb0a2e1a35fdd4e75512ef89e230f9";

	/**
	 * <b>获取最新accessToken<b>
	 *
	 * @return String
	 */
	public static String getAccessToken() {
		String apiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID
				+ "&secret=" + APP_SECRET;

		JSONObject json = JSONObject.parseObject(HttpUtil.getJsonObjectByUrl(apiUrl));

		String access_token = json.get("access_token") + "";
		System.out.println("access_token:" + access_token);
		return access_token;
	}
}
