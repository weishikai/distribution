package com.dewei.qa.contact;

import io.restassured.response.Response;

import java.util.HashMap;

/**
 * Created By weishikai
 * Date: 2019/9/9
 * Time: 13:24
 */
public class Member extends Contact {

    public Response create(HashMap<String, Object> map) {
        String body = template("/data/member.json", map);
        return getDefaultRequestSpecification().body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }
}
