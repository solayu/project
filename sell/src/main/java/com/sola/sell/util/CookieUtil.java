package com.sola.sell.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    public static void set(HttpServletResponse response,
                             String name, String value,
                             Integer expire
                           ){

        Cookie cookie=new Cookie(name,value);
        cookie.setMaxAge(7200);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    public static Cookie get(HttpServletRequest request,
                             String name
                           ){
       Map<String ,Cookie>  cookieMap=reloadCookieMap(request);
       if(cookieMap.containsKey(name)){
           return cookieMap.get(name);
       }else{
           return null;
       }
    }
    public static Map<String ,Cookie> reloadCookieMap(HttpServletRequest request){

        Map<String,Cookie> cookieMap=new HashMap<>();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
