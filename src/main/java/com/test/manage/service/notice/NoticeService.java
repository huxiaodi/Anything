package com.test.manage.service.notice;

import com.test.manage.model.dto.NoticeDto;
import com.test.manage.model.generator.Notice;
import com.test.manage.model.request.QueryParams;

import java.util.List;

public interface NoticeService {

    void insertNotice(Notice notice);

    void deleteNotice(String id);

    List<NoticeDto> getNoticeListAll(QueryParams queryParams);

    List<Notice> getNoticeList();

    Notice getNoticeById(String id);
}
