package com.cuduchoa.ThiTracNghiem.model;

public class ResultKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column result.test_id
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    private Long testId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column result.nguoidung_id
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    private String nguoidungId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column result.test_id
     *
     * @return the value of result.test_id
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public Long getTestId() {
        return testId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column result.test_id
     *
     * @param testId the value for result.test_id
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void setTestId(Long testId) {
        this.testId = testId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column result.nguoidung_id
     *
     * @return the value of result.nguoidung_id
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public String getNguoidungId() {
        return nguoidungId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column result.nguoidung_id
     *
     * @param nguoidungId the value for result.nguoidung_id
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void setNguoidungId(String nguoidungId) {
        this.nguoidungId = nguoidungId;
    }
}