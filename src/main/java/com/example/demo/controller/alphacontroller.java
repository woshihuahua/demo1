package com.example.demo.controller;

import com.example.demo.service.AlphaService;
import com.example.demo.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")

public class alphacontroller {
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String sayhello(){

        return "Hello Spring boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }


    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());//获取请求方式
        System.out.println(request.getServletPath());//获取请求路径 = /alpha/http
        Enumeration<String>enumeration = request.getHeaderNames();//获取请求行的key
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();//得到名字
            String value = request.getHeader(name);//得到对应的值
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));//传一个参数名叫code

        //返回响应数据
        response.setContentType("text/html；charset=utf-8");//设置返回类型：网页类型文本
        try (PrintWriter writer = response.getWriter())//获取输出流
        {

            writer.write("hello");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //get 请求

    // /student?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit)
    {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    // / student/123
    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
    }
    //POST请求
    //浏览器向服务器提交数据，首先得建立一个静态网页（static）
    //动态（templates）
    @RequestMapping(path = "student",method = RequestMethod.POST)
    @ResponseBody//不加注解默认返回HTML
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }
    //响应动态HTML数据
    @RequestMapping(path = "teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","amy");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");//view指view.html
        return mav;
    }

    //另一种方法
    @RequestMapping(path = "school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","pu");
        model.addAttribute("age","100");
        return"/demo/view";
    }

    //一般在异步请求当中，响应JSON数据
    //JAVA对象 通过JSON字符串  转为JS对象、
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object>getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","zhangsan");
        emp.put("age",23);
        return emp;
    }
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>>List = new ArrayList<>();

        Map<String,Object> emp = new HashMap<>();

        emp = new HashMap<>();
        emp.put("name","zhangsan");
        emp.put("age",23);
        List.add(emp);

        emp = new HashMap<>();
        emp.put("name","lisi");
        emp.put("age",25);
        List.add(emp);

        return List;
    }
    @RequestMapping(path = "/setCookie",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        cookie.setPath("/alpha");
        cookie.setMaxAge(60*10);
        response.addCookie(cookie);
        return "hello";
    }
    @RequestMapping(path = "/setSession",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","bill");
        return "set session";

    }
    @RequestMapping(path = "/getSession",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){

        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "12";

    }
}

