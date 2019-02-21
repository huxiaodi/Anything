package com.dllg.ebpay.dao.generator;

import com.dllg.ebpay.model.generator.Area;
import com.dllg.ebpay.model.generator.AreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaMapper {
    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    long countByExample(AreaExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int deleteByExample(AreaExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int deleteByPrimaryKey(String areaId);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int insert(Area record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int insertSelective(Area record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    List<Area> selectByExample(AreaExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    Area selectByPrimaryKey(String areaId);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Area record, @Param("example") AreaExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByExample(@Param("record") Area record, @Param("example") AreaExample example);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Area record);

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    int updateByPrimaryKey(Area record);
}