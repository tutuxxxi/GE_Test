package cdu.lj.controller;

import cdu.lj.entity.Page;
import cdu.lj.entity.Person;
import cdu.lj.service.PersonService;
import cdu.lj.service.impl.PersonServiceImpl;
import cdu.lj.util.DataCheckUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Bidi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 3:47 下午
 * @description：负责查询的servlet
 * @modified By：
 * @version: 1.0
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static PersonService personService = PersonServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        Page page = null;
        String errorMsg = null;
        Object target = null;


        page = new Page((pageNum == null)||(Integer.valueOf(pageNum) <= 0) ? 1 : Integer.valueOf(pageNum),
                (pageSize == null)||(Integer.valueOf(pageSize) <= 0) ? 5 : Integer.valueOf(pageSize));


        //传入id时只用id查, 否则使用其他三个参数进行查询
        if(id != null){
            target = personService.findById(Integer.valueOf(id));
        }else{
            try{
                Person person = new Person();
                person.setName(req.getParameter("name"));
                person.setGender(req.getParameter("gender"));

                String date = req.getParameter("birthday");
                if(DataCheckUtil.notEmpty(date)){
                    person.setBirthday(format.parse(date));
                }

                personService.findPersonByPerson(person, page);
                target = page;
                req.setAttribute("name", person.getName());
                req.setAttribute("gender", person.getGender());
                req.setAttribute("birthday", person.getBirthday() == null ? "" : format.format(person.getBirthday()));
            }catch (ParseException e) {
                //输入日期不合法，直接返回并提示用户
                errorMsg = "日期格式出错, 请重试, 示例: 2000-06-20";
            }
        }

        //返回结果
        if(errorMsg == null){
            if(target != null){
                req.setAttribute("data", target);
            }
        }else{
            req.setAttribute("msg", errorMsg);
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
