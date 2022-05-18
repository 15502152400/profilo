package per.tom.clientview.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class JsonResultVo implements Serializable {

    private byte status;    //0异常，1正常
    private String message;
    private Object result;
    private Throwable throwable;
    private PagenationVo pagenation;

    private JsonResultVo(){}

    private JsonResultVo(String message,byte status){
        this.message = message;
        this.status = status;
    }

    private JsonResultVo(Object result,byte status){
        this.result = result;
        this.status = status;
    }

    private JsonResultVo(String message,Object result,byte status){
        this.message = message;
        this.result = result;
        this.status = status;
    }

    private JsonResultVo(Object result,byte status,PagenationVo pagenation){
        this.result = result;
        this.status = status;
        this.pagenation = pagenation;
    }

    private JsonResultVo(String message,Object result,byte status,PagenationVo pagenation){
        this.message = message;
        this.result = result;
        this.status = status;
        this.pagenation = pagenation;
    }

    private JsonResultVo(Throwable throwable,byte status){
        this.message = throwable.getMessage();
        this.status = status;
    }

    public JsonResultVo(byte status) {
        this.status = status;
    }

    public static JsonResultVo success(){
        return new JsonResultVo((byte)1);
    }

    public static JsonResultVo success(String message){
        return new JsonResultVo(message,(byte)1);
    }

    public static JsonResultVo success(Object result){
        return new JsonResultVo(result,(byte)1);
    }

    public static JsonResultVo success(String message,Object result){
        return new JsonResultVo(message,result,(byte)1);
    }

    public static JsonResultVo success(Object result,PagenationVo pagenation){
        return new JsonResultVo(result,(byte)1,pagenation);
    }

    public static JsonResultVo success(String message,Object result,PagenationVo pagenation){
        return new JsonResultVo(message,result,(byte)1,pagenation);
    }

    public static JsonResultVo fail(String message){
        return new JsonResultVo(message,(byte)0);
    }

    public static JsonResultVo fail(Throwable throwable){
        return new JsonResultVo(throwable,(byte)0);
    }
}
