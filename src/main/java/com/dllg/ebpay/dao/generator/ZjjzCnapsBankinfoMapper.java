package com.dllg.ebpay.dao.generator;

import com.dllg.ebpay.model.generator.ZjjzCnapsBankinfo;
import com.dllg.ebpay.model.generator.ZjjzCnapsBankinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZjjzCnapsBankinfoMapper {
    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    long countByExample(ZjjzCnapsBankinfoExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int deleteByExample(ZjjzCnapsBankinfoExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int insert(ZjjzCnapsBankinfo record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int insertSelective(ZjjzCnapsBankinfo record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    List<ZjjzCnapsBankinfo> selectByExample(ZjjzCnapsBankinfoExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ZjjzCnapsBankinfo record, @Param("example") ZjjzCnapsBankinfoExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByExample(@Param("record") ZjjzCnapsBankinfo record, @Param("example") ZjjzCnapsBankinfoExample example);
}