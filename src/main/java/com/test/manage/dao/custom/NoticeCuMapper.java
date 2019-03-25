package com.test.manage.dao.custom;

import com.test.manage.model.dto.NoticeDto;
import com.test.manage.model.generator.Notice;
import com.test.manage.model.request.QueryParams;

import java.util.List;

public interface NoticeCuMapper {
    List<NoticeDto> getNoticeListAll(QueryParams queryParams);

    List<Notice> getNoticeList();
}
