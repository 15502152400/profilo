package per.tom.clientview.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import per.tom.clientview.expection.ProfiloException;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class FileUpLoadUtils {

    @Autowired
    AssertBase assertBase;

    @Value("${profilo.filepath}")
    String saveDir;

    public void saveFile(byte[] uploadImgByte,String saveName){

        FileImageOutputStream fileOut = null;
        File saveFile = null;
        try {
            File fileDir = new File(saveDir);
            if (!fileDir.exists()){
                fileDir.mkdirs();
            }
            saveFile = new File(fileDir, saveName);
            if (!saveFile.exists()){
                saveFile.createNewFile();
            }else {
                throw new ProfiloException("服务器忙，请稍后再试");
            }
            fileOut = new FileImageOutputStream(saveFile);
            fileOut.write(uploadImgByte);
            System.out.println(saveFile.getPath());
        } catch (IOException e) {
            throw new ProfiloException("图片格式错误");
        }finally {
            // 关闭流
            try {
                assertBase.assertBase(null == saveFile);
                fileOut.flush();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
                fileOut = null;
            }
        }
    }

}
