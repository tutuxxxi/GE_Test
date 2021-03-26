package cdu.lj.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 12:20 下午
 * @description：连接数据库工具类
 * @modified By：
 * @version: 1.0
 */
public class ConnectionUtil {

    private static DataSource dataSource;

    // 初始化数据库连接池
    static{
        InputStream resourceAsStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            Class.forName(properties.getProperty("jdbc.driver-class-name"));

            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(properties.getProperty("jdbc.url"));
            mysqlDataSource.setUser(properties.getProperty("jdbc.username"));
            mysqlDataSource.setPassword(properties.getProperty("jdbc.password"));

            dataSource = mysqlDataSource;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取配置文件失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("未找到driver-class");
        }
    }

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal();

    public static Connection getConnection() throws SQLException {
        //为了实现Service层的事务处理，使用ThreadLocal实现connection的传递
        if(threadLocal.get() == null){
            threadLocal.set(dataSource.getConnection());
        }
        return threadLocal.get();
    }

    public static void close() throws SQLException {
        Connection connection = threadLocal.get();

        if(connection != null){
            connection.close();
            threadLocal.remove();
        }
    }

    public static void close(Statement statement, ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(statement);
    }

    public static void close(Statement statement){
        try{
            if(statement != null){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
