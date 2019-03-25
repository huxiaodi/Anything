package com.test.manage.service.notice.impl;

import com.test.common.CommonUtil;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
import com.test.manage.dao.custom.NoticeCuMapper;
import com.test.manage.dao.generator.NoticeMapper;
import com.test.manage.model.dto.NoticeDto;
import com.test.manage.model.generator.Notice;
import com.test.manage.model.generator.NoticeExample;
import com.test.manage.model.request.QueryParams;
import com.test.manage.service.notice.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 17:00 2019/3/21
 */
@Service
public class NoticeServiceImpl implements NoticeService {


    @Autowired
    private NoticeCuMapper noticeCuMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private Auth auth;

    @Override
    public void insertNotice(Notice notice) {
        AuthUser currentUser = auth.getCurrentUser();
        if(StringUtils.isEmpty(notice.getSysNoticeId())){
            notice.setSysNoticeId(CommonUtil.uuid());
            notice.setSysNoticeCreateUserId(currentUser.getUserId());
            noticeMapper.insertSelective(notice);
        }else{
            noticeMapper.updateByPrimaryKeySelective(notice);
        }


    }

    @Override
    public void deleteNotice(String id) {
        Notice notice = new Notice();
        notice.setSysNoticeId(id);
        notice.setSysNoticeIsDelete(true);
        noticeMapper.updateByPrimaryKeySelective(notice);
    }

    @Override
    public List<NoticeDto> getNoticeListAll(QueryParams queryParams) {
        return noticeCuMapper.getNoticeListAll(queryParams);
    }

    @Override
    public List<Notice> getNoticeList() {
        return noticeCuMapper.getNoticeList();
    }

    @Override
    public Notice getNoticeById(String id) {
        return  noticeMapper.selectByPrimaryKey(id);
    }
}
