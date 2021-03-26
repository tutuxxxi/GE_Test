package cdu.lj.aop;

import cdu.lj.service.PersonService;
import cdu.lj.util.ConnectionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 2:09 下午
 * @description：invocationHandler
 * @modified By：
 * @version: 1.0
 */
public class MyInvocationHandler implements InvocationHandler {
    private PersonService personService;

    public MyInvocationHandler(PersonService personService){
        this.personService = personService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        try{
            Connection connection = ConnectionUtil.getConnection();

            // 如果涉及到非find操作 insert update delete，需要设置开启事务
            if(!method.getName().contains("find")){
                connection.setAutoCommit(false);
                Object res = null;

                try{
                    res = method.invoke(personService, args);
                    connection.commit();
                }catch (Throwable t){
                    //发生异常自动回滚
                    t.printStackTrace();
                    connection.rollback();
                }

                return res;
            }else{
                return method.invoke(personService, args);
            }
        }finally {
            ConnectionUtil.close();
        }
    }
}
