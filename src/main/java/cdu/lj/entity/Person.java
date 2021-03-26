package cdu.lj.entity;

import java.util.Date;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 1:16 下午
 * @description：person实体
 * @modified By：
 * @version: 1.0
 */
public class Person {

    private Integer id;

    private String name;

    private String gender;

    private Date birthday;

    private String telephone;

    private String professions;

    private String remarks;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProfessions() {
        return professions;
    }

    public void setProfessions(String professions) {
        this.professions = professions;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Person() {
    }

    public Person(String name, String gender, Date birthday, String telephone, String professions, String remarks) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.telephone = telephone;
        this.professions = professions;
        this.remarks = remarks;
    }

    public Person(Integer id, String name, String gender, Date birthday, String telephone, String professions, String remarks) {
        this(name, gender, birthday, telephone, professions, remarks);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", telephone='" + telephone + '\'' +
                ", professions='" + professions + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
