package cn.itheima.health.controller;

import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.resources.RedisConstant;
import com.itcast.utils.QiniuUtils;
import com.itcast.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/uploadImage.do") // uploadImage
    public Result uploadImage(@RequestParam("imgFile")MultipartFile file){
        Result result = null;
        if (!file.isEmpty()){
             try {
                String name = file.getOriginalFilename();
                //System.out.println("name为空+***********************************************");
                String  fileName = UploadUtils.generateRandonFileName(name);
                QiniuUtils.upload2Qiniu(file.getBytes(),fileName);
                 Jedis jedis = jedisPool.getResource();
                 jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
                 jedis.close();
                 result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
                System.out.println(result+"1*************************************************************************************************************************");
             } catch (IOException e) {
             e.printStackTrace();
             result = new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
             System.out.println(result+"2*************************************************************************************************************************");
            }
    } else {
            result = new Result(false, "未选择图片");
            System.out.println(result+"3*************************************************************************************************************************");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++");
        return result;
    }

}
