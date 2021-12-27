package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

//数据库工具类
public class DbUtil
{
    private String dbUrl = "jdbc:mysql://10.4.177.120:3306/1112atm信息";//数据库连接地址
    private String dbUserName = "root"; //用户名
    private String dbPassword = "111111"; //密码
    private String jdbcName = "com.mysql.cj.jdbc.Driver"; //驱动名称

    //获取数据库连接
    public Connection getCon() throws Exception
    {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    //关闭数据库连接
    public void closeCon(Connection con) throws Exception
    {
        if (con != null)
        {
            con.close();
        }
    }
    //提示
    public static void main(String[] args)
    {
        DbUtil dbUtil = new DbUtil();
        try
        {
            dbUtil.getCon();
            System.out.println("数据库连接成功！");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
    }
}