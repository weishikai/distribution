package com.dewei.qa.testcase;
import java.util.HashMap;

import org.testng.annotations.BeforeTest;

import com.alibaba.fastjson.JSONObject;
import com.dewei.qa.utils.AesUtil;
import com.dewei.qa.utils.EncrptUtil;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
public class MainTest {
	
	static {
		RestAssured.baseURI = "http://example.com";
	}
	// 
	public static String getrequestData(HashMap<String, String> testparam,JSONObject requestparm,String password) {
		// 接口数据加入parameter
		JSONObject contentjson = new JSONObject();
		contentjson.put("parameter", testparam);
		requestparm.put("content", contentjson);
		String oldData = requestparm.toJSONString();
		System.out.println("content加密前的数据: "+oldData);
		System.out.println("查看一下加密的密钥: "+password);

		// 对测试数据进行加密和处理
		String result = EncrptUtil.encrpt(oldData, password);
		System.out.println("全部请求加密后的body内容: "+result);
		return result;
	}
	// 登陆
	
	public static void testLogin() {
		JSONObject requestparam= new JSONObject();
		requestparam.put("code", "10000");
		requestparam.put("type", "android");
		requestparam.put("device_id", "cb6685f3-4370-3664-8dbb-41655ff55644");
		requestparam.put("time", System.currentTimeMillis());
		requestparam.put("token", "android");
		System.out.println("请求的参数JSON(除了content里面的内容): " + requestparam.toJSONString());
		
		// 测试接口数据
		String password="DWERP@#12$3458ta";
		HashMap<String, String> loginparam= new HashMap<String, String>();
		loginparam.put("loginMethod", "2");
		loginparam.put("packageName", "com.e_dewin.distribution");
		loginparam.put("appVersion", "17");
		loginparam.put("mobile", "18767191571");
		loginparam.put("password", "000000");
		loginparam.put("appType", "1");

		// 发送数据拿到
		Response response = (Response) given().body(getrequestData(loginparam,requestparam,password)).when().post("apiservice").getBody();
		String var =response.path("content");
		String token = response.path("token");
		System.out.println("解密后的数据："+AesUtil.aesDecrypt(var, password));
		System.out.println("token is :"+token);


		// 把token值更新一下 14028库存查询接口
		requestparam.put("token", token);
//		requestparam.put("code", "12042");
//		requestparam.put("code", "12046");
		requestparam.put("code", "12044");
		requestparam.put("time", System.currentTimeMillis());
		// 测试接口数据
		HashMap<String, String> testdataparam= new HashMap<>();
		testdataparam.put("userId", "204defbb3c9e4400927b7c4cfb63743f");
//		testdataparam.put("busareaId", "5bceaff289da42c58d11ff1216226590");
		// 
		testdataparam.put("storeId", "1cb0df3e376a40c8b38f8f1e16870bdb");
//		testdataparam.put("pageSize", "");
//		testdataparam.put("curPage", "");
		// 12046换货单处理
		testdataparam.put("exchangeId", "c1117c9a3e664534a1d0468a682b71df");
		// 12044换货单详情
//		testdataparam.put("exchangeId", "e722f11264f045bc89bd80d5b178a58d");
		//发送数据拿到
		response = (Response) given().body(getrequestData(testdataparam,requestparam,password)).when().post("apiservice").getBody();
		System.out.println("response: "+response.asString());
		String statuscode = response.path("statusCode");
		if("600".equals(statuscode)) {
			System.out.println("库存查询接口14028: 成功请求和返回");
		}
		var =response.path("content");
		System.out.println("库存查询-目录类型14028接口解密后的数据："+AesUtil.aesDecrypt(var, password));
		
	}
	
	public static void main(String[] args) {
		testLogin();
	}

}
