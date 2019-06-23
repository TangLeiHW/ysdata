package ml.tanglei.ysdata.VO;

import lombok.Data;

@Data
public class ResultVO {

    //响应码
    private Integer code;
    //提示信息
    private String msg;
    //具体内容
    private Object data;

    public static ResultVO success(Object data){
        ResultVO result = new ResultVO();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO fail(Integer code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
