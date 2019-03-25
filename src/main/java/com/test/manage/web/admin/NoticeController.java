package com.test.manage.web.admin;

import com.github.pagehelper.PageInfo;
import com.test.framework.controller.BaseController;
import com.test.framework.model.ApiResult;
import com.test.manage.model.generator.Notice;
import com.test.manage.model.request.QueryParams;
import com.test.manage.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 16:57 2019/3/21
 */
@RequestMapping("/notice")
@RestController
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/insertNotice")
    public ApiResult insertNotice(@RequestBody Notice notice){
        noticeService.insertNotice(notice);
        return success();
    }

    @GetMapping("/deleteNotice")
    public ApiResult deleteNotice(@RequestParam String id){
        noticeService.deleteNotice(id);
        return success();
    }

    @PostMapping("/getNoticeListAll")
    public ApiResult getNoticeListAll(@RequestBody QueryParams queryParams){
        return success(new PageInfo<>(noticeService.getNoticeListAll(queryParams)));
    }

    @GetMapping("/getNoticeList")
    public ApiResult getNoticeList(){
        return success(noticeService.getNoticeList());
    }

    @GetMapping("/getNoticeById")
    public ApiResult getNoticeById(@RequestParam String id){
        return success(noticeService.getNoticeById(id));
    }


}
