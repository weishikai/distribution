package com.dewei.qa;

import java.util.HashMap;

/**
 * Created By weishikai
 * Date: 2019/9/5
 * Time: 21:21
 */
public class Restful {
    public String url;
    public String method;
    public HashMap<String, String> headers;
    public HashMap<String, String> query=new HashMap<String, String>();
    public String body;
}
