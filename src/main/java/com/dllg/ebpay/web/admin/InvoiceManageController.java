package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.form.FpMainForm;
import com.dllg.ebpay.service.invoice.InvoiceManageService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.github.pagehelper.PageInfo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 发票管理
 */
@RestController
@RequestMapping("/invoiceManage")
public class InvoiceManageController extends BaseController {

    @Autowired
    private InvoiceManageService invoiceManageService;

    /**
     * 导入Excel
     * @param impFile
     * @param userId
     */
    @PostMapping("/imp")
    public ApiResult imp(MultipartFile[] impFile, String userId) {

        invoiceManageService.impFile(impFile);

        return success("导入成功");
    }



    /*@PostMapping("/generateXml")
    public ApiResult generateXml(@RequestBody FpMainForm fpMainForm,HttpServletResponse response) throws UnsupportedEncodingException{

        String xml = invoiceManageService.getGenerateXml(fpMainForm);


        *//*byte[] bytes = xml.getBytes("utf-8");
        byte[] bytes2 = new String(bytes, "utf-8").getBytes("gbk");
                       xml=new String(bytes2, "gbk");*//*

        try {
            Document doc = DocumentHelper.parseText(this.formatXml(xml));
            //doc.setXMLEncoding("gbk");
            InputStream fis = new ByteArrayInputStream(doc.asXML().getBytes());

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();

            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");

            response.addHeader("Content-Length", "" + doc.asXML().getBytes().length);
            response.addHeader("Content-Disposition", "attachment;filename=exp.xml");
            response.setContentType("application/xml");
            //response.setCharacterEncoding("gbk");
            response.flushBuffer();
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
            os.close();

        }catch (DocumentException e){
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success("下载成功");
    }*/


    /**
     * 格式化XML文件
     * @param xml
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private  String formatXml(String xml) throws DocumentException, IOException{

        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
        //创建输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //制定输出xml的编码类型
        //format.setEncoding("gbk");

        StringWriter writer = new StringWriter();
        //创建一个文件输出流
        XMLWriter xmlwriter = new XMLWriter( writer, format );
        //将格式化后的xml串写入到文件
        xmlwriter.write(document);
        String returnValue = writer.toString();
        writer.close();

        //返回编译后的字符串格式
        return returnValue;
    }


    @PostMapping("/getFpMainList")
    public ApiResult getFpMainList(@RequestBody FpMainForm fpMainForm) {
        List<Map<String, Object>> list = invoiceManageService.getFpMainList(fpMainForm);
        return success(new PageInfo<>(list));
    }


    @PostMapping("/getFpDetailList")
    public ApiResult getFpDetailList(@RequestBody FpMainForm fpMainForm) {
        List<Map<String, Object>> list = invoiceManageService.getFpDetailList(fpMainForm);
        return success(new PageInfo<>(list));
    }








    @RequestMapping(value="generateXml",method=RequestMethod.POST)
    public void downloadXml(String[] djh, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, DocumentException {

        FpMainForm fpMainForm = new FpMainForm();
        fpMainForm.setDjhs(djh);
        String xml = invoiceManageService.getGenerateXml(fpMainForm);
        Document doc = DocumentHelper.parseText(xml);

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        format.setIndent(true);
        format.setNewlines(true);
        format.setExpandEmptyElements(true); //设置双标签 /默认空单标签

        XMLWriter writer = null;
        writer = new XMLWriter(response.getOutputStream(), format);
        writer.setEscapeText(false);
        response.reset();
        response.setCharacterEncoding("GBK");
        response.setContentType("application/x-msdownload");
        // 对文件进行url编码
        String fileName = "发票管理.xml";
        if (  request.getHeader("user-agent").toLowerCase().contains("msie")
           || request.getHeader("user-agent").toLowerCase().endsWith("like gecko")// ie10以上取消了msie用户代理字符串
                ) {
            fileName = URLEncoder.encode(fileName, "UTF-8"); // IE
        } else {
            fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1"); // 非IE
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        writer.write(doc);
        writer.close();
    }


}
