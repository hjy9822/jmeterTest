package com.example.jmetertest.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class jmeterTest {

    @GetMapping("/jmeterTest")
    public String test(HttpSession session, HttpServletRequest req){
        String session_id = String.valueOf(session.getAttribute("session_id"));
        //String session_idCookie = String.valueOf(req.getCookies());
        return "hello world";
    }

    @RequestMapping(value = "/jmeterTestLogin", method = RequestMethod.POST)
    public Map<String,Object> test2(@RequestParam String id, @RequestParam  String pw, HttpSession session, HttpServletResponse response) throws Exception{
        Map result = new HashMap<String,Object>();
        String ID= id;
        String PW = pw;
        session.setAttribute("session_id",ID+"_NAME" );
        Cookie cookie = new Cookie("session_id",String.valueOf(session.getAttribute("session_id")));
        response.addCookie(cookie);

        result.put("ID",ID);
        result.put("PW",pw);
        result.put("name",session.getAttribute("session_id"));

        return result;
    }

    @RequestMapping(value = "/jmeterTestLogout", method = RequestMethod.POST)
    public boolean test3(HttpSession session, HttpServletResponse response) throws Exception{
        boolean logOut = false;

        try {
            session.invalidate();
            Cookie cookie = new Cookie("session_id",null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            logOut = true;
        }catch (Exception e){
            throw e;
        }
        return  logOut;
    }
}
