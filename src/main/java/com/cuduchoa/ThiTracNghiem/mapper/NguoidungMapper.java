package com.cuduchoa.ThiTracNghiem.mapper;

import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.NguoidungExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface NguoidungMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    long countByExample(NguoidungExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int deleteByExample(NguoidungExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int deleteByPrimaryKey(String nguoidungId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int insert(Nguoidung row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int insertSelective(Nguoidung row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    List<Nguoidung> selectByExample(NguoidungExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    Nguoidung selectByPrimaryKey(String nguoidungId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByExampleSelective(@Param("row") Nguoidung row, @Param("example") NguoidungExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByExample(@Param("row") Nguoidung row, @Param("example") NguoidungExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByPrimaryKeySelective(Nguoidung row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    int updateByPrimaryKey(Nguoidung row); 

	List<Map<String, Object>> getAllStudentOrderByClassName();

	List<Map<String, Object>> getAllStudentOrderByUserState();

	List<Map<String, Object>> getAllStudent();

	List<Map<String, Object>> getAllTeacherOrderByClassName();

	List<Map<String, Object>> getAllTeacherOrderByUserState();

	List<Map<String, Object>> getAllTeacher();
}