package com.dewei.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
/**
 * Created By weishikai
 * Date: 2019/9/5
 * Time: 21:26
 */

public class WeworkConfig {
    public String agentId="1000005";
    public String secret="1JPyY9GvPLZfpvxEDjok-Xt_9v7HIBYJhZUoO6EgNGY";
    public String corpid = "wwd6da61649bd66fea";
    public String contactSecret="C7uGOrNyxWWzwBsUyWEbLQdOqoWPz4hNvxj9RIFv-4U";

    public String current="test";
    public HashMap<String, HashMap<String, String>> env;

    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if(weworkConfig==null){
            weworkConfig=load("/conf/Config.yaml");
            System.out.println(weworkConfig);
            System.out.println(weworkConfig.agentId);
        }
        return weworkConfig;
    }

    public static WeworkConfig load(String path){
        //fixed: read from yaml or json
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(WeworkConfig.class.getResourceAsStream(path), WeworkConfig.class);
            //System.out.println(mapper.writeValueAsString(WeworkConfig.getInstance()));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
