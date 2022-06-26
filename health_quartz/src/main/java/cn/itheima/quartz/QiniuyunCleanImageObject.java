package cn.itheima.quartz;


import com.itcast.resources.RedisConstant;
import com.itcast.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@Component
public class QiniuyunCleanImageObject {

    /**
     * 方法的作用：去除reids中，多余的diff，删除七牛云上多余的图片
     */

    @Autowired JedisPool jedisPool;

    public void run(){

        System.out.println("++++++++++++********************++++++++++++++++++++");
        Jedis jedis = jedisPool.getResource();
        Set<String> sdiff = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        ArrayList<String> list = new ArrayList<>(sdiff);
        String[] arr = {};
        String[] array = list.toArray(arr);
        String[] array1 = sdiff.toArray(arr);
        System.out.println(Arrays.toString(array)+"++++++++++++********************++++++++++++++++++++");
        System.out.println(Arrays.toString(array1)+"++++++++++++********************++++++++++++++++++++");
        for (String filename : array) {
        QiniuUtils.deleteFileFromQiniu(filename);
        }
        if(array.length>0) {
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES, array);
        }
        jedis.close();
    }
}
