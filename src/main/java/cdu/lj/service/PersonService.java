package cdu.lj.service;

import cdu.lj.entity.Page;
import cdu.lj.entity.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 1:15 下午
 * @description：PersonService
 * @modified By：
 * @version:
 */
public interface PersonService {

    /**
     * 通过person条件查询person
     *  接收参数： 姓名、性别和生日
     *  当存在多个参数时会发生&运算
     * @param person
     * @return
     */
    List<Person> findPersonByPerson(Person person);

    /**
     * 通过person条件查询person
     *  接收参数： 姓名、性别和生日
     *  当存在多个参数时会发生&运算
     * @param person
     * @param page
     * @return
     */
    List<Person> findPersonByPerson(Person person, Page page);


    /**
     * 添加用户信息
     * @param person
     * @return
     * @throws SQLException
     */
    boolean addPerson(Person person) throws SQLException;


    /**
     * 修改用户信息
     * @param person
     * @return
     * @throws SQLException
     */
    boolean modifyPerson(Person person) throws SQLException;


    /**
     * 删除用户信息
     * @param id
     * @return
     * @throws SQLException
     */
    boolean deletePerson(int id) throws SQLException;

    /**
     * 获取所有用户
     * @return
     * @throws SQLException
     */
    List<Person> findAll();


    /**
     * 获取所有用户
     * @param page
     * @return
     * @throws SQLException
     */
    List<Person> findAll(Page page);


    /**
     * 查询单个用户
     * @param id
     * @return
     * @throws SQLException
     */
    Person findById(Integer id);

}
