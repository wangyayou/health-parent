package cn.itheima.health.controller;

import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.MemberService;
import com.itcast.utils.DateUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    private MemberService memberService;
@RequestMapping("/getMemberReport.do")
    public Result getMemberReport(){
        try {
            Map<String, List> map = memberService.getMemberReport();
            System.out.println("map = " + map);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS);
        }

    }


    @RequestMapping("/getMemberReportX.do")
    public Result getMemberReportX(@RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime){
        try {
            Map<String, List> map = memberService.getMemberReportX(startTime,endTime);
            System.out.println("map = " + map);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS);
        }

    }
}
