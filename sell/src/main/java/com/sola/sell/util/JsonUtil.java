package com.sola.sell.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//Json格式化
public class JsonUtil {

    public static String jsonUtil(Object object){

        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);
    }
}
