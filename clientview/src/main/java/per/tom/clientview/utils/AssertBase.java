package per.tom.clientview.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import per.tom.clientview.config.ProfiloConfig;
import per.tom.clientview.expection.ProfiloException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Component
public class AssertBase {

    public void assertBase(boolean statement,String message){
        if (statement){
            throw new ProfiloException(message);
        }
    }

    public void assertBase(boolean statement){
        if (statement){
            throw new ProfiloException("服务器忙，请稍后再试");
        }
    }

    // 数字类型校验
    public void asserNumber(Integer number,Integer maxvalue,Integer minValue,String message){
        assertBase(number==null||number > maxvalue||number < minValue,message);
    }

    public void asserNumber(Integer number,Integer maxvalue,Integer minValue){
        assertBase(number==null||number > maxvalue||number < minValue,"服务器忙,请稍后再试");
    }

    public void asserNumber(Long number, Long maxvalue, Long minValue, String message){
        assertBase(number==null||number > maxvalue||number < minValue,message);
    }

    // 字符串类型校验
    public void asserString(String target,Integer maxSize,Integer minSize,String enCode,String message){
        try {
            int l = target.getBytes(enCode).length;
            assertBase(null==target||"".equals(target)||l>maxSize||l<minSize,message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ProfiloException("服务器忙，请稍后再试");
        }
    }
    // 字符串类型校验 u8
    public void asserString(String target,Integer maxSize,Integer minSize,String message){
        try {
            int l = target.getBytes("UTF-8").length;
            assertBase(null==target||"".equals(target)||l>maxSize||l<minSize,message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ProfiloException("服务器忙，请稍后再试");
        }
    }
    // 字符串类型校验 u8
    public void asserString(String target,Integer maxSize,Integer minSize){
        try {
            int l = target.getBytes("UTF-8").length;
            assertBase(null==target||"".equals(target)||l>maxSize||l<minSize,"服务器忙，请稍后再试");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ProfiloException("服务器忙，请稍后再试");
        }
    }

    // 用户密码校验 密码至少包含数字和英文，长度6-20
    public void assertPass(String p){
        if(!Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$",p)) {
            throw new ProfiloException("密码格式错误");
        }
    }

    // 用户手机校验
    public void assertTel(String t){
        if(!Pattern.matches("^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$",t)) {
            throw new ProfiloException("手机号格式错误");
        }
    }

    // 列表校验
    public void assertList(List list,String message){
        assertBase(list.size()<1||null == list, message);
    }

    // 图片宽高校验
    public void assertImgWH(BufferedImage image,Integer height,Integer width){
        assertBase(image.getWidth() != width);
        assertBase(image.getHeight() != height);
    }

    // 文件格式校验
    public void assertFileType(String targetType,String[] typeArray,String message){
        boolean isPass = false;
        targetType = targetType.toLowerCase();
        for (int i=0;i<typeArray.length;i++){
            if (targetType.equals(typeArray[i])){
                isPass = true;
            }
        }
        if (null == message){
            assertBase(isPass == false,"服务器忙，请稍后");
        }else {
            assertBase(isPass == false,message);
        }
    }

    // 数组类型校验
    public void asserArray(Object[] targetArray,String message){
        assertBase(targetArray.length<1||null==targetArray,message);
    }

    public void asserArray(Object[] targetArray){
        assertBase(targetArray.length<1||null==targetArray,"服务器忙，请稍后再试");
    }

}
