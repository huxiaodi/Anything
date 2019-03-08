package com.test.manage.service.sequence;

/**
 * Created by hugo on 2016/10/18.
 */
public interface SequenceService {
    /***
     *
     * @author hugo
     * @Description: 获取下一个
     * @param sequenceName
     * @return
     * @throws
     */
    Integer next(String sequenceName);

    /***
     *
     * @author hugo
     * @Description: 获取当前
     * @param sequenceName
     * @return
     * @throws
     */
    Integer now(String sequenceName);

    /***
     *
     * @author hugo
     * @Description: 创建一个序列
     * @param sequenceName
     * @param sequenceDesc
     * @return
     * @throws
     */
    void init(String sequenceName, String sequenceDesc);

    /***
     *
     * @author hugo
     * @Description: 创建一个序列
     * @param sequenceName
     * @return
     * @throws
     */
    void init(String sequenceName);

    /***
     *
     * @author hugo
     * @Description: 生成单号
     * @param sequenceName
     * @param length
     * @param sequenceDesc
     * @return
     * @throws
     */
    String generateNo(String sequenceName, Integer length, String sequenceDesc);

    /***
     *
     * @author hugo
     * @Description: 生成单号
     * @param sequenceName
     * @param length
     * @return
     * @throws
     */
    String generateNo(String sequenceName, Integer length);
}
