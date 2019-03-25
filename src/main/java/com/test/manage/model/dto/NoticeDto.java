package com.test.manage.model.dto;

import com.test.manage.model.generator.Notice;
import lombok.Data;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 17:07 2019/3/21
 */
@Data
public class NoticeDto extends Notice {
    private String sysNoticeCreateUserName;
}
