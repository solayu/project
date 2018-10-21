package com.sola.sell.util;

import com.sola.sell.vo.ResultVo;
import com.sola.sell.vo.ResultVo;

public class ResultVoUtil {
    public static ResultVo success(Object object){
        ResultVo resultVo=new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }
    public static ResultVo success(){

        return success(null);
    }
    public static ResultVo error(Integer code,String msg){

        ResultVo resultVo=new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
