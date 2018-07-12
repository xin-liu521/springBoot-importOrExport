package com.velvol.salary.smsutils;
public class Md5Digest {

	/**
	 * 数字签名(明文MD5加密)
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		
		String plaintext = "123456";
		
		String result = client.getDigest(plaintext);
		
		System.out.println(result);
	}

}
