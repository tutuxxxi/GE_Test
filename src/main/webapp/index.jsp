<%@ page import="cdu.lj.entity.Page" %>
<%@ page import="cdu.lj.entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>index</title>
        <style>
            #outer{
                margin: 0 auto;
                width: 600px;
                font-family: verdana,arial,sans-serif;
                font-size:11px;
                color:#333333;
            }
            #dataTable {
                border-width: 1px;
                border-color: #999999;
                border-collapse: collapse;
                text-align: center;
                font-family: verdana,arial,sans-serif;
                font-size:11px;
                color:#333333;
                width: 100%;
            }
            #dataTable th {
                background:#b5cfd2 ;
                border-width: 1px;
                padding: 8px;
                border-style: solid;
                border-color: #999999;
            }
            #dataTable td {
                background:#dcddc0 ;
                border-width: 1px;
                padding: 8px;
                border-style: solid;
                border-color: #999999;
            }
            #page{
                margin-top: 1em
            }

            #page input{
                width: 3em;
            }

            #search{
                text-align: center;
                margin-top: 2em
            }

            #search input{
                width: 10em;
            }
        </style>
    </head>
    <body>
        <jstl:if test="${data != null}">
            <div id="outer">
                <table id="dataTable">
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>gender</th>
                        <th>birthday</th>
                        <th>telephone</th>
                        <th>professions</th>
                        <th>remarks</th>
                    </tr>
                    <jstl:forEach items="${data.data}" var="person">
                        <tr>
                            <td>${person.id}</td>
                            <td>${person.name}</td>
                            <td>${person.gender}</td>
                            <td>${person.birthday}</td>
                            <td>${person.telephone}</td>
                            <td>${person.professions}</td>
                            <td>${person.remarks}</td>
                        </tr>
                    </jstl:forEach>
                </table>
                <div id="page">
                    总数据量: ${data.total}
                    当前页数: <input id="pageNum" type="text" value="${data.pageNum}" name="pageNum">
                    分页大小: <input id="pageSize" type="text" value="${data.pageSize}" name="pageSize">
                    <jstl:if test="${data.pageNum != 1}">
                        <a href="">上一页</a>
                    </jstl:if>
                    <jstl:if test="${data.pageNum != data.maxPage}">
                        <a href="">下一页</a>
                    </jstl:if>
                </div>
                <div id="search">
                    <p style="font-size: 15px">指定查询条件</p><br>
                    姓名: <input type="text" value="${name}" name="name"><br>
                    性别: <input type="text" value="${gender}" name="gender"><br>
                    生日: <input type="text" value="${birthday}" name="birthday"><br>
                </div>
            </div>
        </jstl:if>


    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(":input").on("blur", query)

        function query(){
            //无论是谁失去焦点，都进行一次表单的提交

            const inputs = $(":input")
            const parameters = new Map();

            $(":input").each(function(){
                const value = $(this).val();
                if(value != undefined && value != ""){
                    parameters.set($(this).context.name, value)
                }
            });

            let url = "${pageContext.request.contextPath}" + "/query"

            if(parameters.size != 0){
                url = url.concat("?");
                for(let key of parameters.keys()){
                    console.log()
                    url = url.concat(key, "=", parameters.get(key), "&");
                }
                url = url.substring(0, url.length-1)
            }

            window.location.href = url;
        }


        $('a').click(function () {
            console.log($(this).text())
            if($(this).text() == "上一页"){
                $("#pageNum").val(parseInt($("#pageNum").val()) - 1);
            }else{
                $("#pageNum").val(parseInt($("#pageNum").val()) + 1);
            }
            query();
            return false;
        })
    </script>
    </body>
</html>
