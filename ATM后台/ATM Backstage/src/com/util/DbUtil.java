package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

//���ݿ⹤����
public class DbUtil
{
    private String dbUrl = "jdbc:mysql://10.4.177.120:3306/1112atm��Ϣ";//���ݿ����ӵ�ַ
    private String dbUserName = "root"; //�û���
    private String dbPassword = "111111"; //����
    private String jdbcName = "com.mysql.cj.jdbc.Driver"; //��������

    //��ȡ���ݿ�����
    public Connection getCon() throws Exception
    {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    //�ر����ݿ�����
    public void closeCon(Connection con) throws Exception
    {
        if (con != null)
        {
            con.close();
        }
    }
    //��ʾ
    public static void main(String[] args)
    {
        DbUtil dbUtil = new DbUtil();
        try
        {
            dbUtil.getCon();
            System.out.println("���ݿ����ӳɹ���");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("���ݿ�����ʧ�ܣ�");
        }
    }
}