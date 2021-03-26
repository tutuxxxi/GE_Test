package cdu.lj.dao.impl;

import cdu.lj.aop.MyPageHelper;
import cdu.lj.dao.PersonDao;
import cdu.lj.entity.Page;
import cdu.lj.entity.Person;
import cdu.lj.util.ConnectionUtil;
import cdu.lj.util.DataCheckUtil;
import sun.security.jca.GetInstance;

import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 1:23 下午
 * @description：personDao实现接口
 * @modified By：
 * @version: 1.0
 */
public class PersonDaoImpl implements PersonDao {

    private static PersonDao personDao = new PersonDaoImpl();
    private static PersonDao proxy = new PersonDaoImpl();
    private Page page;

    static{
        proxy = (PersonDao) Proxy.newProxyInstance(PersonDaoImpl.class.getClassLoader(),
                PersonDaoImpl.class.getInterfaces(),
                new MyPageHelper(personDao));
    }

    private PersonDaoImpl(){}

    public static PersonDao getInstance(){return proxy;}



    @Override
    public void setPage(Page page){
        this.page = page;
    }

    @Override
    public Page getPage() {
        return page;
    }

    @Override
    public List<Person> selectAll() {
        String sql = "select * from tb_person";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Person> res = null;

        try{
            Connection connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            res = new ArrayList<>();
            while(resultSet.next()){
                res.add(new Person(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getString("gender"), resultSet.getDate("birthday"),
                        resultSet.getString("telephone"), resultSet.getString("professions"),
                        resultSet.getString("remarks")));
            }

        }finally {
            ConnectionUtil.close(preparedStatement, resultSet);
            return res;
        }
    }

    @Override
    public Person selectById(int id) {
        String sql = "select * from tb_person where id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Person res = null;

        try{
            Connection connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                res = new Person(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getString("gender"), resultSet.getDate("birthday"),
                        resultSet.getString("telephone"), resultSet.getString("professions"),
                        resultSet.getString("remarks"));
            }

        }finally {
            ConnectionUtil.close(preparedStatement, resultSet);
            return res;
        }
    }

    @Override
    public List<Person> selectByPerson(Person person) {
        //通过person中拥有的属性，动态的构建sql

        StringBuilder sql = new StringBuilder("select * from tb_person where ");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Person> res = null;


        //先放入过滤范围较大的准确匹配
        if(person.getBirthday() != null){
            sql.append("birthday = ? and ");
        }

        if(DataCheckUtil.notEmpty(person.getGender())){
            sql.append("gender = ? and ");
        }
        //模糊查询放最后
        if(DataCheckUtil.notEmpty(person.getName())){
            sql.append("`name` like concat('%',?,'%') and ");
        }

        sql.append("1=1");
        int index = 1;

        try{
            Connection connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            if(person.getBirthday() != null){
                preparedStatement.setDate(index++, new java.sql.Date(person.getBirthday().getTime()));
            }
            if(DataCheckUtil.notEmpty(person.getGender())){
                preparedStatement.setString(index++, person.getGender());
            }
            if(DataCheckUtil.notEmpty(person.getName())){
                preparedStatement.setString(index++, person.getName());
            }

            resultSet = preparedStatement.executeQuery();

            res = new ArrayList<>();
            while(resultSet.next()){
                res.add(new Person(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getString("gender"), resultSet.getDate("birthday"),
                        resultSet.getString("telephone"), resultSet.getString("professions"),
                        resultSet.getString("remarks")));
            }

        }finally {
            ConnectionUtil.close(preparedStatement, resultSet);
            return res;
        }
    }

    @Override
    public int insert(Person person) throws SQLException {
        String sql = "insert into tb_person(`name`, gender, birthday, telephone, professions, remarks) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int i = 0;

        try{
            Connection connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getGender());
            preparedStatement.setDate(3,new java.sql.Date(person.getBirthday().getTime()));
            preparedStatement.setString(4,person.getTelephone());
            preparedStatement.setString(5,person.getProfessions());
            preparedStatement.setString(6,person.getRemarks());

            i = preparedStatement.executeUpdate();

            //主键回填
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                person.setId(resultSet.getInt(1));
            }
        }finally {
            ConnectionUtil.close(preparedStatement, resultSet);
            return i;
        }
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String sql = "delete from tb_person where id = ?";
        PreparedStatement preparedStatement = null;
        int i = 0;

        try{
            Connection connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            i = preparedStatement.executeUpdate();

        }finally {
            ConnectionUtil.close(preparedStatement);
            return i;
        }
    }

    @Override
    public int update(Person person) throws SQLException {
        String sql = "update tb_person set name=?, gender=?, birthday=?, telephone=?, professions=?, remarks=? where id=?";
        PreparedStatement preparedStatement = null;
        int i = 0;

        try{
            Connection connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getGender());
            preparedStatement.setDate(3,new java.sql.Date(person.getBirthday().getTime()));
            preparedStatement.setString(4,person.getTelephone());
            preparedStatement.setString(5,person.getProfessions());
            preparedStatement.setString(6,person.getRemarks());
            preparedStatement.setInt(7,person.getId());

            i = preparedStatement.executeUpdate();

        }finally {
            ConnectionUtil.close(preparedStatement);
            return i;
        }
    }
}
