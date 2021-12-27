package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.model.Admin;

public class AdminDao {
    //��¼��֤
    public Admin login(Connection con,Admin admin)throws Exception
    {
        Admin resultUser = null;
        String sql="select * from admin where account=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);//Ԥ����
        pstmt.setString(1, admin.getAccount());
        pstmt.setString(2, admin.getPassword());
        ResultSet rs = pstmt.executeQuery();//�õ�������
        if(rs.next())
        {
            resultUser = new Admin();//��ȡ�����id account password
            resultUser.setId(rs.getInt("id"));
            resultUser.setAccount(rs.getString("account"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}
