package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.dao.AdminDao;
import com.model.Admin;
import com.util.DbUtil;
import com.util.StringUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class LogOnFrm extends JFrame
{

    private JPanel contentPane;
    private JTextField accountTxt;
    private JPasswordField passwordTxt;
    private DbUtil dbUtil = new DbUtil();
    private AdminDao adminDao = new AdminDao();


    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    LogOnFrm frame = new LogOnFrm();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    //建表
    public LogOnFrm()
    {
        setTitle("后台管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //插入中国银行的图片
        JLabel lblAtm = new JLabel(" ");
        lblAtm.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/中国银行.jpg")));
        lblAtm.setFont(new Font("微软雅黑", Font.BOLD, 40));
        lblAtm.setBounds(100, 20, 800, 100);
        contentPane.add(lblAtm);

        //设置账户标签
        JLabel label = new JLabel("账户:");
        label.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/account_avatar_person_profile_user_24px_1225506_easyicon.net.png")));
        label.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label.setBounds(146, 137, 88, 31);
        contentPane.add(label);

        //设置输入账号的文本框
        accountTxt = new JTextField();
        accountTxt.setFont(new Font("楷体", Font.PLAIN, 18));
        accountTxt.setColumns(10);
        accountTxt.setBounds(234, 137, 249, 32);
        contentPane.add(accountTxt);

        //设置密码标签
        JLabel label_1 = new JLabel("密码:");
        label_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/key_lock_open_password_unlock_24px_1225503_easyicon.net.png")));
        label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_1.setBounds(146, 225, 88, 31);
        contentPane.add(label_1);

        //设置密码文本框
        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(234, 226, 249, 32);
        contentPane.add(passwordTxt);

        //设置登录按钮
        JButton btnLogin = new JButton("登录");
        btnLogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                loginActionPerformed(e);
            }
        });
        btnLogin.setFont(new Font("微软雅黑", Font.BOLD, 20));
        btnLogin.setBounds(128, 311, 125, 47);
        contentPane.add(btnLogin);

        //设置重置按钮
        JButton button = new JButton("重置");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                resetValueActionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.BOLD, 20));
        button.setBounds(381, 311, 125, 47);
        contentPane.add(button);

        //设置JFrame居中显示
        this.setLocationRelativeTo(null);
    }


    //登录事件处理
    private void loginActionPerformed(ActionEvent evt)
    {
        String account = this.accountTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StringUtil.isEmpty(account))
        {
            JOptionPane.showMessageDialog(null, "账号不能为空！");
            return;
        }
        if (StringUtil.isEmpty(password))
        {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return;
        }
        Admin admin = new Admin(account,password);
        Connection con = null;
        try
        {
            con = dbUtil.getCon();
            Admin currentUser = adminDao.login(con,admin);
            if(currentUser != null)
            {
                dispose();
                MainFrm mainFrm = new MainFrm(admin);
                mainFrm.setVisible(true);
            }else
            {
                JOptionPane.showMessageDialog(null, "账号错误或者密码错误！");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                dbUtil.closeCon(con);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    //重置事件处理
    private void resetValueActionPerformed(ActionEvent evt)
    {
        this.accountTxt.setText("");
        this.passwordTxt.setText("");
    }
}
