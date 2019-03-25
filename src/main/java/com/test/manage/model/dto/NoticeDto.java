package com.test.manage.model.dto;

import com.test.manage.model.generator.Notice;
import lombok.Data;

@Data
public class NoticeDto extends Notice {
    private String sysNoticeCreateUserName;
}
