package com.cuduchoa.ThiTracNghiem.mapper;

import com.cuduchoa.ThiTracNghiem.model.Test_detailExample;
import com.cuduchoa.ThiTracNghiem.model.Test_detailKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface Test_detailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    long countByExample(Test_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int deleteByExample(Test_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int deleteByPrimaryKey(Test_detailKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int insert(Test_detailKey row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int insertSelective(Test_detailKey row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    List<Test_detailKey> selectByExample(Test_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByExampleSelective(@Param("row") Test_detailKey row, @Param("example") Test_detailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_detail
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByExample(@Param("row") Test_detailKey row, @Param("example") Test_detailExample example);
}