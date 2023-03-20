package com.cuduchoa.ThiTracNghiem.mapper;

import com.cuduchoa.ThiTracNghiem.model.Class_detailExample;
import com.cuduchoa.ThiTracNghiem.model.Class_detailKey;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface Class_detailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    long countByExample(Class_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int deleteByExample(Class_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int deleteByPrimaryKey(Class_detailKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int insert(Class_detailKey row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int insertSelective(Class_detailKey row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    List<Class_detailKey> selectByExample(Class_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByExampleSelective(@Param("row") Class_detailKey row, @Param("example") Class_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByExample(@Param("row") Class_detailKey row, @Param("example") Class_detailExample example);
    
}