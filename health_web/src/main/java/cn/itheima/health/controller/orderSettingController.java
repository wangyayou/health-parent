package cn.itheima.health.controller;

import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.orderSettingService;
import com.itcast.utils.POIUtils;
import com.itheima.pojo.OrderSetting;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderSetting")
public class orderSettingController {
    @Reference
    private orderSettingService orderSettingService;

    @RequestMapping("/importOrderSettings.do")
    public Result importOrderSettings(@RequestParam("excelFile")MultipartFile excelFile){
        if (excelFile.isEmpty()){
            return new Result(false,"没有上传文件");
        }else {
            try {
                POIUtils.checkFile(excelFile);
                List<String[]> list = POIUtils.readExcel(excelFile);
                ArrayList<OrderSetting> orderSettingList = new ArrayList<>();
                if (list.size()>0) {
                    // [[1,1],[2,2],[3,3],[4,4]]
                    for (String[] strings : list) {
                        OrderSetting orderSetting = new OrderSetting();
                        orderSetting.setOrderDate(new SimpleDateFormat("yyyy/MM/dd").parse(strings[0]));
                        orderSetting.setNumber(Integer.parseInt(strings[1]));
                        orderSettingList.add(orderSetting);
                }
                    System.out.println("orderSettingList *******************************= " + orderSettingList);
                    orderSettingService.importOrderSettings(orderSettingList);
                    System.out.println("orderSettingList *******************************");
                    return new Result(true, MessageConstant.UPLOAD_SUCCESS);
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.UPLOAD_FAIL);
            }
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }

    }


    @RequestMapping("/GetOneMonthOrderSettings.do")
    public Result GetOneMonthOrderSettings(@RequestParam("StartDate") String startDate ,@RequestParam("EndDate") String endDate){

        try {
            List<Map<String,Object>> mapList =  orderSettingService.GetOneMonthOrderSettings(startDate,endDate);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,mapList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_SUCCESS);
        }

    }

    @RequestMapping("/AddOrderSettingReservations.do")
    public Result AddOrderSettingReservations(@RequestParam("Num") Integer num ,@RequestParam("dateParam")String dateParam){
        try {
            orderSettingService.AddOrderSettingReservations(num,dateParam);
            return new Result(true,MessageConstant.EDIT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_ORDERSETTING_FAIL);
        }

    }


}
