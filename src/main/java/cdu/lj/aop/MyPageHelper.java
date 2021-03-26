package cdu.lj.aop;

import cdu.lj.dao.PersonDao;
import cdu.lj.entity.Page;
import cdu.lj.entity.Person;
import cdu.lj.service.PersonService;
import cdu.lj.util.ConnectionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 4:16 下午
 * @description：分页组件
 * @modified By：
 * @version: 1.0
 */
public class MyPageHelper implements InvocationHandler {

    private PersonDao personDao;
    private String[] pageMethods = {"selectAll", "selectByPerson"};

    public MyPageHelper(PersonDao personDao){
        this.personDao = personDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //满足分页要求的函数
        Object invoke = method.invoke(personDao, args);
        if(method.getName().equals(pageMethods[0]) || method.getName().equals(pageMethods[1])){

            Page page = personDao.getPage();

            if(page != null){
                //返回内容一定为List<Person>
                List<Person> all = (List<Person>) invoke;

                //设置总数据量
                int size = all.size();
                page.setTotal(size);

                if(size != 0){
                    page.setMaxPage((int)Math.ceil(page.getTotal() / (double)page.getPageSize()));


                    //当数据超出时 总是将分页设置到最后一页
                    int start = (page.getPageNum() - 1) * page.getPageSize() + 1;
                    while(start > size){
                        start -= page.getPageSize();
                    }
                    page.setPageNum((start - 1) / page.getPageSize() + 1);


                    //封装数据
                    List<Person> list = new ArrayList<>(page.getPageSize());
                    for(int i = start; i<=Math.min(page.getPageNum() * page.getPageSize(), size); i++){
                        list.add(all.get(i-1));
                    }
                    page.setData(list);

                }else{
                    page.setPageNum(0);
                }
                personDao.setPage(null);
            }
        }
        return invoke;
    }
}
