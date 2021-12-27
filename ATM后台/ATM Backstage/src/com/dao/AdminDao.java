package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.model.Admin;

public class AdminDao {
    //登录验证
    public Admin login(Connection con,Admin admin)throws Exception
    {
        Admin resultUser = null;
        String sql="select * from admin where account=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);//预处理
        pstmt.setString(1, admin.getAccount());
        pstmt.setString(2, admin.getPassword());
        ResultSet rs = pstmt.executeQuery();//得到处理结果
        if(rs.next())
        {
            resultUser = new Admin();//获取结果的id account password
            resultUser.setId(rs.getInt("id"));
            resultUser.setAccount(rs.getString("account"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}
