package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.generator.InterestManager;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.interestManager.InterestManagerService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import com.github.pagehelper.PageInfo;
import com.ibm.icu.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.dllg.ebpay.service.sequence.impl.SequenceServiceImpl.logger;

/**
 * 贴息计算
 */
@RestController
@RequestMapping("interestManager")
public class InterestManagerController extends BaseController {

    @Autowired
    private InterestManagerService interestManagerService;
    @Autowired
    private Auth auth;

    /**
     * 列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {

        queryParams.startPage();
        List<Map<String,Object>> list = interestManagerService.dataList(queryParams);
        return success(new PageInfo<>(list));
    }
    @PostMapping("/listK")
    public ApiResult listK(@RequestBody(required = false) QueryParams queryParams) {

        queryParams.startPage();
        List<Map<String,Object>> listK = interestManagerService.dataListK(queryParams);
        return success(new PageInfo<>(listK));
    }


    /**
     * 修改 保存
     */
    @PostMapping("/save")
    public ApiResult save(@RequestBody(required = false)InterestManager interestManager) {

        String rlt = interestManagerService.save(interestManager, auth.getCurrentUser());
        return success(rlt);
    }

    /**
     * 信息补充 确定
     */
    @PostMapping("/editAgain")
    public ApiResult editAgain(@RequestBody(required = false)InterestManager interestManager) {

        String rlt = interestManagerService.editAgain(interestManager, auth.getCurrentUser());
        return success(rlt);
    }

    /**
     * 结算 确定
     */
    @PostMapping("/setFinish")
    public ApiResult setFinish(@RequestBody(required = false)InterestManager interestManager) {

        String rlt = interestManagerService.setFinish(interestManager, auth.getCurrentUser());
        return success(rlt);
    }


    /**
     * 拆分
     */
    @PostMapping("/copyAdd")
    public ApiResult copyAdd(@RequestBody(required = false)InterestManager interestManager) {

        String rlt = interestManagerService.copyAdd(interestManager, auth.getCurrentUser());
        return success(rlt);
    }


    /**
     * 贴息计算
     */
    @PostMapping("/interestCompute")
    public ApiResult interestCompute(@RequestBody(required = false)InterestManager interestManager) {
        String rlt = interestManagerService.interestCompute(interestManager, auth.getCurrentUser());
        return success(rlt);
    }

    /**
     * 删除
     */
    @PostMapping("/deletes")
    public ApiResult deletes(@RequestBody(required = false) List<String> ids) {

        interestManagerService.deletes(ids);
        return success("删除成功");
    }

    /**
     * 检查主键
     */
    @RequestMapping(value = "/checkKey/{keyValue}", method = RequestMethod.GET)
    public ApiResult checkKey(@PathVariable String keyValue){
        Boolean b = interestManagerService.checkKey(keyValue);
        try {
            return success(b);
        }catch (Exception e){
            return error("检查主键失败");
        }
    }

    /**
     * 导入Excel
     * @param impFile
     * @param userId
     */
    @PostMapping("/imp")
    public ApiResult imp(MultipartFile[] impFile, String userId) {

        String rlt = interestManagerService.impFile(impFile, auth.getCurrentUser());

        return success(rlt);
    }

    /**
     * 分配使用金额
     */
    @PostMapping("/moneyAvg")
    public ApiResult moneyAvg(@RequestBody(required = false)InterestManager interestManager) {

        String rlt = interestManagerService.useMoneyAvg(interestManager, auth.getCurrentUser());
        return success(rlt);
    }


    @PostMapping("/exportForEXCEL123")
    public ApiResult exportForEXCEL123(@RequestBody(required = false)InterestManager interestManager) {
        InterestManager interestManager1 = interestManager;

        String a = interestManager1.getIdArray()[0];

        return success(a);
    }


    /**
     * @param
     * @return
     * @throws
     * @author YOUNG
     * @Description: 导出  excel
     */
    @RequestMapping(value = "/exportForEXCEL" , method = RequestMethod.PUT)
    public void exportForEXCEL(@RequestBody(required = false) InterestManager interestManager, HttpServletResponse response, HttpServletRequest request) {
        try {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(interestManager.getInterestManagerCustomerNameArray()[0]);

            HSSFRow row = sheet.createRow((int) 0);
            row.setHeightInPoints(20);//设置行高
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBottomBorderColor(HSSFColor.BLACK.index);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setLeftBorderColor(HSSFColor.BLACK.index);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setRightBorderColor(HSSFColor.BLACK.index);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setTopBorderColor(HSSFColor.BLACK.index);
            sheet.setColumnWidth(0 , 3000);
            sheet.setColumnWidth(1 , 10000);
            sheet.setColumnWidth(2 , 10000);
            sheet.setColumnWidth(3 , 3000);
            sheet.setColumnWidth(4 , 3000);
            sheet.setColumnWidth(5 , 3000);
            sheet.setColumnWidth(6 , 10000);
            sheet.setColumnWidth(7 , 10000);
            sheet.setColumnWidth(8 , 3000);
            sheet.setColumnWidth(9 , 3000);
            sheet.setColumnWidth(10 , 3000);
//            sheet.setColumnWidth(9 , 8800);

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("收到日期");
            cell.setCellStyle(style);
            cell = row.createCell((short) 1);
            cell.setCellValue("汇票编号");
            cell.setCellStyle(style);
            cell = row.createCell((short) 2);
            cell.setCellValue("前手背书人");
            cell.setCellStyle(style);
            cell = row.createCell((short) 3);
            cell.setCellValue("金额");
            cell.setCellStyle(style);
            cell = row.createCell((short) 4);
            cell.setCellValue("开票日期");
            cell.setCellStyle(style);
            cell = row.createCell((short) 5);
            cell.setCellValue("汇票到期日");
            cell.setCellStyle(style);
            cell = row.createCell((short) 6);
            cell.setCellValue("出票人");
            cell.setCellStyle(style);
            cell = row.createCell((short) 7);
            cell.setCellValue("付款行");
            cell.setCellStyle(style);
            cell = row.createCell((short) 8);
            cell.setCellValue("贴息减免天数");
            cell.setCellStyle(style);
            cell = row.createCell((short) 9);
            cell.setCellValue("贴息率");
            cell.setCellStyle(style);
            cell = row.createCell((short) 10);
            cell.setCellValue("贴息金额");
            cell.setCellStyle(style);
            cell = row.createCell((short) 11);
            cell.setCellValue("备注");
            cell.setCellStyle(style);

            BigDecimal b = new BigDecimal(0);
            String money;
            String interest;
            String freeDay;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < interestManager.getIdArray().length; i++ ){
                row = sheet.createRow(i + 1);
                row.setHeightInPoints(15);//设置行高

                b = new BigDecimal( interestManager.getInterestManagerFreeDayArray()[i] ); //判断免息天数是否为空
                if (b == null || b.compareTo(new BigDecimal(0)) == 0){
                    freeDay = "";
                }else {
                    freeDay = b.toString();
                }

                b = interestManager.getInterestManagerMoneyArray()[i]; //判断贴息金额是否为空
                if (b == null){
                    money = "";
                }else {
                    money = b.toString();
                }

                b = interestManager.getInterestManagerInterestArray()[i]; //判断贴息率是否为空
                if (b == null){
                    interest = "";
                }else {
                    interest = b.toString();
                }

                cell = row.createCell(0 ); cell.setCellValue(sdf.format(interestManager.getInterestManagerDateGetArray()[i])); cell.setCellStyle(style);
                cell = row.createCell(1 ); cell.setCellValue(interestManager.getInterestManagerTicketNoArray()[i]); cell.setCellStyle(style);
                cell = row.createCell(2 ); cell.setCellValue(interestManager.getInterestManagerCustomerNameArray()[i]); cell.setCellStyle(style);
                cell = row.createCell(3 ); cell.setCellValue(interestManager.getInterestManagerMoneyAllArray()[i].toString()); cell.setCellStyle(style);
                cell = row.createCell(4 ); cell.setCellValue(sdf.format(interestManager.getInterestManagerDateStartArray()[i])); cell.setCellStyle(style);
                cell = row.createCell(5 ); cell.setCellValue(sdf.format(interestManager.getInterestManagerDateEndArray()[i])); cell.setCellStyle(style);
                cell = row.createCell(6 ); cell.setCellValue(interestManager.getInterestManagerTicketUserArray()[i]); cell.setCellStyle(style);
                cell = row.createCell(7 ); cell.setCellValue(interestManager.getInterestManagerTicketBankArray()[i]); cell.setCellStyle(style);
                cell = row.createCell(8 ); cell.setCellValue(freeDay); cell.setCellStyle(style);
                cell = row.createCell(9 ); cell.setCellValue(interest); cell.setCellStyle(style);
                cell = row.createCell(10 ); cell.setCellValue(money); cell.setCellStyle(style);
                cell = row.createCell(11 ); cell.setCellValue(interestManager.getInterestManagerMemoArray()[i]); cell.setCellStyle(style);
           }


            // 第六步，将文件存到指定位置
            Workbook ww = wb;
            ww.write(response.getOutputStream());
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setCharacterEncoding("UTF-8");
            response.flushBuffer();

        }catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取导出贴息数据信息失败", e);
        }

    }





    //获取品种下拉
    @GetMapping("/getKindDictSelect")
    private ApiResult getKindDictSelect(){
        return success(interestManagerService.getKindDictSelect());
    }

    //获取品种下拉
    @GetMapping("/getCustomerDictSelect")
    private ApiResult getCustomerDictSelect(){
        return success(interestManagerService.getCustomerDictSelect());
    }

    //合同月份下拉
    @GetMapping("/getInterestDictSelect")
    private ApiResult getInterestDictSelect() { return success(interestManagerService.getInterestDictSelect());}


}
