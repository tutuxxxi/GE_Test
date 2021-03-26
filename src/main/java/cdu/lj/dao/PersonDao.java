package cdu.lj.dao;

import cdu.lj.entity.Page;
import cdu.lj.entity.Person;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 1:15 下午
 * @description：PersonDao
 * @modified By：
 * @version:
 */
public interface PersonDao {


    /**
     * 开启一个分页
     * @param page
     * @return
     */
    void setPage(Page page);

    /**
     * 获得分页
     * @return
     */
    Page getPage();

    /**
     * 查询所有用户
     * @return
     */
    List<Person> selectAll();


    /**
     * 查询用户
     * @param id
     * @return
     */
    Person selectById(int id);

    /**
     * 查询用户 可指定字段为name gender birthday
     * @param person
     * @return
     */
    List<Person> selectByPerson(Person person);


    /**
     * 添加用户
     * @param person
     * @return
     * @throws SQLException
     */
    int insert(Person person) throws SQLException;


    /**
     * 删除用户
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteById(int id) throws SQLException;


    /**
     * 更新用户
     * @param person
     * @return
     * @throws SQLException
     */
    int update(Person person) throws SQLException;



}
