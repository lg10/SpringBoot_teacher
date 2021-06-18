package config.exceptionhandler;



import config.CustomException.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.R;


@ControllerAdvice//
class GlobalExceptionHandler {
    //指定出现异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }



    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }


    @ExceptionHandler(CustomException.class)
    @ResponseBody//为了返回数据
    public R error(CustomException e){
        e.printStackTrace();//加载到日志文件中去
//        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMsg());//传什么值，抛什么值
    }
}
