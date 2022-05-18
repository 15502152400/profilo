package per.tom.clientview.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import per.tom.clientview.config.ProfiloConfig;
import per.tom.clientview.expection.ProfiloException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class ImageUtils {

    @Autowired
    private ProfiloConfig profiloConfig;

    @Autowired
    private FileUpLoadUtils fileUpLoadUtils;

    @Value("${profilo.fileshowpath}")
    private String fileShowPath;

    public String saveImg(MultipartFile uploadImg){
        InputStream fileIPS = null;
        String showPath;
        try {
            // 加载图片
            fileIPS = uploadImg.getInputStream();
            String imgName = uploadImg.getOriginalFilename();
            String imgType = imgName.substring(imgName.lastIndexOf(".") + 1);
            String saveName = UUID.randomUUID().toString() + "." + imgType;

            // 校验图片宽高
            AssertBase.assertImgWH(ImageIO.read(fileIPS),profiloConfig.getProfiloCoverImgHeight(),profiloConfig.getProfiloCoverImgWidth());
            // 校验图片大小
            AssertBase.asserNumber(uploadImg.getSize(), profiloConfig.getProfiloCoverSizeMax(), profiloConfig.getProfiloCoverSizeMin(), "图片大小不合法");
            // 校验图片格式
            AssertBase.assertFileType(imgType,profiloConfig.getCoverImgType(),"图片格式错误");

            // 上传图片
            fileUpLoadUtils.saveFile(uploadImg.getBytes(), saveName);
            showPath = fileShowPath+"/"+saveName;

        } catch (IOException e) {
            throw new ProfiloException("图片格式错误");
        }finally {
            // 关闭流
            try {
                fileIPS.close();
            } catch (IOException e) {
                e.printStackTrace();
                fileIPS = null;
            }
        }
        return showPath;
    }

}
