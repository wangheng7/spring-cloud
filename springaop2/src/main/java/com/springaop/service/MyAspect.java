package com.springaop.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springaop.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
@Aspect
public class MyAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAspect.class);

    @Autowired
    ILogService logService;

    @Pointcut("execution(* com.springaop..*.*(..))")
    private void serviceAspect(){
        System.out.println("point cut start");
    }

    /*
    * ProceedingJoinPoint
    * */
    @Around("serviceAspect()")
    public Object around(ProceedingJoinPoint point){
        Log log = new Log();
        //获取方法参数
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;

        //拦截的实体类，就是当前正在执行的service
        Object target = point.getTarget();

        //拦截的方法名称。当前正在执行的方法
        String methodName = point.getSignature().getName();

        // 拦截的方法参数
        Object[] args = point.getArgs();

        Class[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(null != method){
            LogFilter logFilter = method.getAnnotation(LogFilter.class);

            log.setModuleName(logFilter.module());
            log.setOperaName(logFilter.opera());

            Object arg = args[0];
            String s = JSONObject.toJSONString(arg);
            JSONObject afterJson = JSONObject.parseObject(s);
//            JSONArray operateParamArray = new JSONArray();
//            for(int i = 0; i < args.length; i++){
//                Object paramsObj = args[i];
//                if(paramsObj instanceof String || paramsObj instanceof JSONObject){
//                    String str = (String) paramsObj;
//                    JSONObject dataJson = JSONObject.parseObject(str);
//                    if(dataJson == null || dataJson.isEmpty() || "null".equals(dataJson)){
//                        break;
//                    }else {
//                        operateParamArray.add(dataJson);
//                    }
//                }else if(paramsObj instanceof Map){
//                    Map<String, Object> map = (Map<String, Object>) paramsObj;
//                    JSONObject json =new JSONObject(map);
//                    operateParamArray.add(json);
//                }
//            }
            //设置请求参数
//            log.setOperateParams(operateParamArray.toJSONString());

            String id = afterJson.get(logFilter.id()).toString();
            //被代理的类名
            String className = point.getSignature().getDeclaringTypeName();

            method.invoke(args,);
        }

        logService.addLog(log);
    }
}
