package com.dllg.ebpay.dao.generator;

import com.dllg.ebpay.model.generator.BusinessTrade;
import com.dllg.ebpay.model.generator.BusinessTradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTradeMapper {
    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    long countByExample(BusinessTradeExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int deleteByExample(BusinessTradeExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int deleteByPrimaryKey(String businessTradeId);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int insert(BusinessTrade record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int insertSelective(BusinessTrade record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    List<BusinessTrade> selectByExample(BusinessTradeExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    BusinessTrade selectByPrimaryKey(String businessTradeId);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BusinessTrade record, @Param("example") BusinessTradeExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByExample(@Param("record") BusinessTrade record, @Param("example") BusinessTradeExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BusinessTrade record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByPrimaryKey(BusinessTrade record);
}