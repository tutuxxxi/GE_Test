package cdu.lj.service.impl;

import cdu.lj.aop.MyInvocationHandler;
import cdu.lj.dao.PersonDao;
import cdu.lj.dao.impl.PersonDaoImpl;
import cdu.lj.entity.Page;
import cdu.lj.entity.Person;
import cdu.lj.service.PersonService;
import cdu.lj.util.ConnectionUtil;
import cdu.lj.util.DataCheckUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 1:54 下午
 * @description：PersonService实现类
 * @modified By：
 * @version: 1.0
 */
public class PersonServiceImpl implements PersonService {

    private static PersonDao personDao = PersonDaoImpl.getInstance();
    private static PersonService personService = new PersonServiceImpl();
    private static PersonService proxy;

    static{
        proxy = (PersonService) Proxy.newProxyInstance(personService.getClass().getClassLoader(),
                personService.getClass().getInterfaces(),
                new MyInvocationHandler(personService));
    }

    private PersonServiceImpl(){}

    public static PersonService getInstance(){
        return proxy;
    }


    @Override
    public List<Person> findPersonByPerson(Person person) {
        if(DataCheckUtil.notEmpty(person.getName()) ||
            DataCheckUtil.notEmpty(person.getGender()) || person.getBirthday() != null){
            return personDao.selectByPerson(person);
        }else{
            return findAll();
        }
    }


    @Override
    public List<Person> findPersonByPerson(Person person, Page page) {
        personDao.setPage(page);
        return findPersonByPerson(person);
    }


    @Override
    public boolean addPerson(Person person) throws SQLException {
        //判断必填字段
        if(DataCheckUtil.notEmpty(person.getName())){
            return personDao.insert(person) != 0;
        }
        return false;
    }

    @Override
    public boolean modifyPerson(Person person) throws SQLException {
        if(person.getId() != null){
            return personDao.update(person) != 0;
        }
        return false;
    }

    @Override
    public boolean deletePerson(int id) throws SQLException {
        return personDao.deleteById(id) != 0;
    }


    @Override
    public List<Person> findAll(){
        return personDao.selectAll();
    }

    @Override
    public List<Person> findAll(Page page){
        personDao.setPage(page);
        return findAll();
    }

    @Override
    public Person findById(Integer id){
        if(id != null){
            return personDao.selectById(id);
        }
        return null;
    }
}
