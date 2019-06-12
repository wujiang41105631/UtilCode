package com.xcn.spring.annotation.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 展示ControllerAdvice 的作用
 *
 * 通过这个注释可以将Controller的全局配置放在同一个位置，
 * 注解了@Controller类的方法可以用@ExceptionHandler,@InitBinder,@ModelAttribute注解到方法上，
 * 这对于所有注解了@RequestMapping的方法有效
 * @ExceptionHandler 用户全局控制器内的异常
 * @InitBinder 用来设置 WebDataBinder,WebDataBinder用来自动绑定前台请求参数到Model中
 * @ModelAttribute 本来作用是绑定键值对到Model里，此处是让全局的@RequestMapping都能或得在此处设置的键值对
 *
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
@ControllerAdvice
public class ControllerAdviceTest {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        return null;
    }

    @ModelAttribute
    public void addAttribute(Model model){

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }
}
