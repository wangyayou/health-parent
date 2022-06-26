package cn.itheima.health.controller;

import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.ReportService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class reportController {
    @Reference
    private ReportService reportService;


    @RequestMapping("/findExcelDatabase.do")
    public Result findSetMealReport(){
        try {
            Map map = reportService.findExcelDatabase();
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }

    }


    @RequestMapping("/exportExcelDatabase.do")
    public void exportExcelDatabase(HttpSession session , HttpServletResponse response, @RequestParam("filename") String filename){
        try {
            // 俩个头一个流
            ServletContext context = session.getServletContext();
            String realPath = context.getRealPath("/template/" + filename);
            XSSFWorkbook sheets = new XSSFWorkbook(realPath);
            XSSFSheet sheetAt = sheets.getSheetAt(0); // 拿到第一个工作区
            sheetAt.getRow(1).getCell(1).setCellValue("");  // 拿到一个工作区中的 第2行第二列的单元格并赋值
            String mimeType = context.getMimeType(filename);
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","attachment;filename="+filename);
            ServletOutputStream out = response.getOutputStream();
            sheets.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
