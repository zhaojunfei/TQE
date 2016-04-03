package com.tqe.base.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maple on 2016/3/12.
 */
@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

    Log logger = LogFactory.getLog(MyExceptionHandler.class);

    private  static  Map<String,String> messageConvertMap = new HashMap<String,String>();
    static {

        messageConvertMap.put("OLE2","EXCEL的格式错误，不能解析，请转换后再试");
        messageConvertMap.put("Data too long","您输入的数据太长了！");
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("msg", messageConvert(ex.getMessage()));
        logger.error("出错了",ex);
        return modelAndView;
    }


    /**
     * 把英文系统异常转换成 可读的消息提醒
     */
    public String messageConvert(String errorMsg){

        if(StringUtils.isBlank(errorMsg)){
            return "未知的错误";
        }

        for(String keyValue:messageConvertMap.keySet()){
            if(errorMsg.contains(keyValue)){
                String msg = messageConvertMap.get(keyValue);
                msg = "<h2>"+msg+"</h2>";
                errorMsg = msg +"\n"+errorMsg;
                break;
            }
        }
        return errorMsg;


    }
}