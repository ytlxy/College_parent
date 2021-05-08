package com.at.serviceBash.exceptionhandler;

import com.at.commonUtils.R;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public R error(MethodArgumentTypeMismatchException e){
        e.printStackTrace();
        return R.error().message("参数类型错误");
    }

    @ExceptionHandler(BoomException.class)
    @ResponseBody
    public R error(BoomException e){
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public R error(DataIntegrityViolationException e){
        e.printStackTrace();
        return R.error().message("简介数据过长，或上传图片过大");
    }
}
