package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.model.Card;
import com.util.StringUtil;

//CardDao
public class CardDao
{ //银行卡添加
    public int add(Connection con, Card card) throws Exception
    {
        String sql = "insert into card(account,password,name,sex,age,idCard,balance) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, card.getAccount());
        pstmt.setString(2, card.getPassword());
        pstmt.setString(3, card.getName());
        pstmt.setString(4, card.getSex());
        pstmt.setString(5, card.getAge());
        pstmt.setString(6, card.getIdCard());
        pstmt.setString(7, card.getBalance());
        return pstmt.executeUpdate();
    }
    //向User表里添加新用户
    public int add1(Connection con, Card user) throws Exception
    {
        String sql = "insert into user(account,password) values(?,?)";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, user.getAccount());
        pstmt.setString(2, user.getPassword());
        return pstmt.executeUpdate();
    }
    //是否存在账户
    public boolean accountSearch(Connection con, String account)throws Exception
    {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    //删除Card表银行卡
    public int delete(Connection con, String account) throws Exception
    {
        String sql = "delete from card where account=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, account);
        return pstmt.executeUpdate();
    }
    //删除User表银行卡
    public int delete1(Connection con, String account) throws Exception
    {
        String sql = "delete from User where account=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, account);
        return pstmt.executeUpdate();
    }

    //更新银行卡
    public int update(Connection con, Card card) throws Exception
    {
        String sql = "update card set password=?,name=?,sex=?,age=?,idCard=?,balance=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, card.getPassword());
        pstmt.setString(2, card.getName());
        pstmt.setString(3, card.getSex());
        pstmt.setString(4, card.getAge());
        pstmt.setString(5, card.getIdCard());
        pstmt.setString(6, card.getBalance());
        pstmt.setString(7, card.getAccount());
        return pstmt.executeUpdate();
    }

    //用户信息查询
    public ResultSet listAll(Connection con, Card card) throws Exception
    {
        StringBuffer sb = new StringBuffer("select * from card");
        if (StringUtil.isNotEmpty(card.getAccount()))
        {
            sb.append(" and account like " + card.getAccount());
        }
        if (StringUtil.isNotEmpty(card.getName()))
        {
            sb.append(" and name like '%" + card.getName() + "%'");
        }
        if(StringUtil.isNotEmpty(card.getIdCard()))
        {
            sb.append(" and idCard like " + card.getIdCard());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    //查询单个用户信息
    public ResultSet list(Connection con, String account) throws Exception
    {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        return pstmt.executeQuery();
    }

    //查询余额
    public String checkBalance(Connection con, String account) throws Exception
    {
        String sql = "select balance from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next())
        {
            balance = rs.getString("balance");
        }
        return balance;
    }

    //存款
    public int deposit(Connection con, String account, String deposit) throws Exception
    {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next())
        {
            balance = rs.getString("balance");
        }
        String sql1 = "update card set balance=? where account=?";
        PreparedStatement pstmt1 = con.prepareCall(sql1);
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) + Double.valueOf(deposit)));
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    //取款
    public int withdraw(Connection con, String account, String withdraw) throws Exception
    {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql.toString());
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next())
        {
            balance = rs.getString("balance");
        }
        String sql1 = "update card set balance=? where account=?";
        PreparedStatement pstmt1 = con.prepareCall(sql1);
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) - Double.valueOf(withdraw)));
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    //匹配原密码
    public boolean password(Connection con, String account, String password) throws Exception
    {
        String sql = "select password from card where account like " + account;
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        String originalPassword = null;
        while (rs.next())
        {
            originalPassword = rs.getString("password");
        }
        return originalPassword.equals(password);
    }

    //修改card表里的密码
    public int passwordChange(Connection con, String account, String password) throws Exception
    {
        String sql = "update card set password=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }

    //修改user表里的密码
    public int passwordChange1(Connection con, String account, String password) throws Exception
    {
        String sql = "update user set password=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }
}

