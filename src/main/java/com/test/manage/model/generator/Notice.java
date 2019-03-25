package com.test.manage.model.generator;

import java.util.Date;

/**
 *   sys_notice
 * @mbg.generated do_not_delete_during_merge
 */
public class Notice {
    /**
     *   id
     *   sys_notice.sys_notice_id
     * @mbg.generated
     */
    private String sysNoticeId;

    /**
     *   标题
     *   sys_notice.sys_notice_title
     * @mbg.generated
     */
    private String sysNoticeTitle;

    /**
     *   内容
     *   sys_notice.sys_notice_content
     * @mbg.generated
     */
    private String sysNoticeContent;

    /**
     *   发布时间
     *   sys_notice.sys_notice_issue_time
     * @mbg.generated
     */
    private Date sysNoticeIssueTime;

    /**
     *   结束时间
     *   sys_notice.sys_notice_end_time
     * @mbg.generated
     */
    private Date sysNoticeEndTime;

    /**
     *   发布人
     *   sys_notice.sys_notice_create_user_id
     * @mbg.generated
     */
    private String sysNoticeCreateUserId;

    /**
     *   创建时间
     *   sys_notice.sys_notice_create_time
     * @mbg.generated
     */
    private Date sysNoticeCreateTime;

    /**
     *   是否删除 0-否，1-是
     *   sys_notice.sys_notice_is_delete
     * @mbg.generated
     */
    private Boolean sysNoticeIsDelete;

    /**
     * id
     * @return the value of sys_notice.sys_notice_id
     * @mbg.generated
     */
    public String getSysNoticeId() {
        return sysNoticeId;
    }

    /**
     * id
     * @param sysNoticeId the value for sys_notice.sys_notice_id
     * @mbg.generated
     */
    public void setSysNoticeId(String sysNoticeId) {
        this.sysNoticeId = sysNoticeId == null ? null : sysNoticeId.trim();
    }

    /**
     * 标题
     * @return the value of sys_notice.sys_notice_title
     * @mbg.generated
     */
    public String getSysNoticeTitle() {
        return sysNoticeTitle;
    }

    /**
     * 标题
     * @param sysNoticeTitle the value for sys_notice.sys_notice_title
     * @mbg.generated
     */
    public void setSysNoticeTitle(String sysNoticeTitle) {
        this.sysNoticeTitle = sysNoticeTitle == null ? null : sysNoticeTitle.trim();
    }

    /**
     * 内容
     * @return the value of sys_notice.sys_notice_content
     * @mbg.generated
     */
    public String getSysNoticeContent() {
        return sysNoticeContent;
    }

    /**
     * 内容
     * @param sysNoticeContent the value for sys_notice.sys_notice_content
     * @mbg.generated
     */
    public void setSysNoticeContent(String sysNoticeContent) {
        this.sysNoticeContent = sysNoticeContent == null ? null : sysNoticeContent.trim();
    }

    /**
     * 发布时间
     * @return the value of sys_notice.sys_notice_issue_time
     * @mbg.generated
     */
    public Date getSysNoticeIssueTime() {
        return sysNoticeIssueTime;
    }

    /**
     * 发布时间
     * @param sysNoticeIssueTime the value for sys_notice.sys_notice_issue_time
     * @mbg.generated
     */
    public void setSysNoticeIssueTime(Date sysNoticeIssueTime) {
        this.sysNoticeIssueTime = sysNoticeIssueTime;
    }

    /**
     * 结束时间
     * @return the value of sys_notice.sys_notice_end_time
     * @mbg.generated
     */
    public Date getSysNoticeEndTime() {
        return sysNoticeEndTime;
    }

    /**
     * 结束时间
     * @param sysNoticeEndTime the value for sys_notice.sys_notice_end_time
     * @mbg.generated
     */
    public void setSysNoticeEndTime(Date sysNoticeEndTime) {
        this.sysNoticeEndTime = sysNoticeEndTime;
    }

    /**
     * 发布人
     * @return the value of sys_notice.sys_notice_create_user_id
     * @mbg.generated
     */
    public String getSysNoticeCreateUserId() {
        return sysNoticeCreateUserId;
    }

    /**
     * 发布人
     * @param sysNoticeCreateUserId the value for sys_notice.sys_notice_create_user_id
     * @mbg.generated
     */
    public void setSysNoticeCreateUserId(String sysNoticeCreateUserId) {
        this.sysNoticeCreateUserId = sysNoticeCreateUserId == null ? null : sysNoticeCreateUserId.trim();
    }

    /**
     * 创建时间
     * @return the value of sys_notice.sys_notice_create_time
     * @mbg.generated
     */
    public Date getSysNoticeCreateTime() {
        return sysNoticeCreateTime;
    }

    /**
     * 创建时间
     * @param sysNoticeCreateTime the value for sys_notice.sys_notice_create_time
     * @mbg.generated
     */
    public void setSysNoticeCreateTime(Date sysNoticeCreateTime) {
        this.sysNoticeCreateTime = sysNoticeCreateTime;
    }

    /**
     * 是否删除 0-否，1-是
     * @return the value of sys_notice.sys_notice_is_delete
     * @mbg.generated
     */
    public Boolean getSysNoticeIsDelete() {
        return sysNoticeIsDelete;
    }

    /**
     * 是否删除 0-否，1-是
     * @param sysNoticeIsDelete the value for sys_notice.sys_notice_is_delete
     * @mbg.generated
     */
    public void setSysNoticeIsDelete(Boolean sysNoticeIsDelete) {
        this.sysNoticeIsDelete = sysNoticeIsDelete;
    }
}