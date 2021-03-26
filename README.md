## 运行说明：

index.jsp中通过jquery监听，当input失去焦点自动提交查询， 通过controller获取筛选的用户信息。因此直接修改input即可实现按条件查询。



## 文件结构说明

```sh
./
├── README.md
├── tb_person.sql																				# 数据库转储文件
├── Test.iml
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── cdu
    │   │       └── lj
    │   │           ├── aop
    │   │           │   ├── MyInvocationHandler.java    # 实现事务管理的InvocationHandler
    │   │           │   └── MyPageHelper.java						# 实现分页的InvocationHandler
    │   │           ├── controller
    │   │           │   └── QueryServlet.java						# 查询servlet
    │   │           ├── dao															# PersonDao包
    │   │           │   ├── PersonDao.java							
    │   │           │   └── impl
    │   │           │       └── PersonDaoImpl.java
    │   │           ├── entity				
    │   │           │   ├── Page.java										# 分页对象
    │   │           │   └── Person.java									# person实体
    │   │           ├── filter
    │   │           │   └── EncodingFilter.java					# 编码过滤器
    │   │           ├── service													# PersonService包
    │   │           │   ├── PersonService.java					
    │   │           │   └── impl
    │   │           │       └── PersonServiceImpl.java
    │   │           └── util		
    │   │               ├── ConnectionUtil.java					# 连接池工具类
    │   │               └── DataCheckUtil.java					# 数据检查工具类
    │   ├── resources
    │   │   └── jdbc.properties													# 数据库连接参数
    │   └── webapp
    │       ├── WEB-INF
    │       │   └── web.xml
    │       └── index.jsp
    └── test
        └── java
```

