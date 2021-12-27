package com.view;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.dao.CardDao;
import com.model.Card;
//import com.sun.org.glassfish.gmbal.ManagedData;
import com.model.Admin;
import com.util.DbUtil;
import com.util.ExcelExporter;
import com.util.StringUtil;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class MainFrm extends JFrame {
    private JPanel contentPane;
    private JTable CardTable;
    private JRadioButton manJrb;
    private JRadioButton womanJrb;
    private JRadioButton manAddJrb;
    private JRadioButton womanAddJrb;
    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();
    private JTextField accountTxt;
    private JTextField nameTxt;
    private JTextField IDCardTxt;
    private JTextField accountTxt_1;
    private JTextField passwordTxt;
    private JTextField idCardTxt;
    private JTextField nameTxt_1;
    private JTextField ageTxt;
    private JTextField balanceTxt;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField accountAddTxt;
    private JTextField passwordAddTxt;
    private JTextField idCardAddTxt;
    private JTextField nameAddTxt;
    private JTextField ageAddTxt;
    private JTextField balanceAddTxt;
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    //启动应用程序
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    MainFrm frame = new MainFrm(new Admin());
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    //创建框架
    public MainFrm(Admin admin)
    {
        setResizable(false); //设置无法缩放
        setTitle("用户信息管理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //关闭并销毁 而不是退出
        setBounds(100, 100, 690, 654);
        contentPane = new JPanel();//实例化一个面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);//在本窗体CardQueryFrm中加入面板
        contentPane.setLayout(null);
        //加一个带有滚动条的面板
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 664, 150);
        contentPane.add(scrollPane);
        //建一个数据以表格的形式显示给用户
        CardTable = new JTable();
        CardTable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent met)
            {
                CardTableMousePressed(met);
            }
        });
        CardTable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        CardTable.setModel(new DefaultTableModel(
                new Object[][] {},
                //设置JTable的列名
                new String[]
                {
                        "账号", "密码", "姓名", "性别", "年龄", "身份证号", "余额"
                }
        ) {
            boolean[] columnEditables = new boolean[]
            {
                    false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column)
            {
                return columnEditables[column];
            }
        });
        //自定义JTable列的宽度
        CardTable.getColumnModel().getColumn(2).setPreferredWidth(64);
        CardTable.getColumnModel().getColumn(3).setPreferredWidth(49);
        CardTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        CardTable.getColumnModel().getColumn(5).setPreferredWidth(91);
        CardTable.getColumnModel().getColumn(6).setPreferredWidth(90);
        scrollPane.setViewportView(CardTable);//向滚动面板中添加JTable
        //在面板上添加"搜索条件"面板
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "搜索条件", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 10, 664, 52);
        contentPane.add(panel);
        panel.setLayout(null);
        //在面板上添加"账号："文本
        JLabel lblNewLabel = new JLabel("账号：");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel.setBounds(18, 23, 51, 15);
        panel.add(lblNewLabel);
        //设置文本框
        accountTxt = new JTextField();
        accountTxt.setBounds(63, 20, 108, 21);
        panel.add(accountTxt);
        accountTxt.setColumns(10);
        //在面板上添加"姓名："文本
        JLabel label = new JLabel("姓名：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        label.setBounds(191, 23, 51, 15);
        panel.add(label);
        //设置文本框
        nameTxt = new JTextField();
        nameTxt.setColumns(10);
        nameTxt.setBounds(233, 20, 72, 21);
        panel.add(nameTxt);
        //设置查询按钮
        JButton btnNewButton = new JButton("查询");
        btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cardSearchActionPerformed(e);
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnNewButton.setIcon(new ImageIcon(MainFrm.class.getResource("/images/查询.png")));
        btnNewButton.setBounds(536, 10, 118, 33);
        panel.add(btnNewButton);
        //在面板上添加"身份证号："文本
        JLabel label_1 = new JLabel("身份证号：");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        label_1.setBounds(326, 23, 80, 15);
        panel.add(label_1);
        //设置文本框
        IDCardTxt = new JTextField();
        IDCardTxt.setColumns(10);
        IDCardTxt.setBounds(403, 21, 123, 21);
        panel.add(IDCardTxt);
        //在面板上添加"表单操作"面板
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(10, 256, 664, 150);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        //在面板上添加"账号："文本
        JLabel label_2 = new JLabel("账号：");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_2.setBounds(28, 28, 54, 15);
        panel_1.add(label_2);
        //设置文本框
        accountTxt_1 = new JTextField();
        accountTxt_1.setEditable(false);
        accountTxt_1.setBounds(68, 25, 100, 21);
        panel_1.add(accountTxt_1);
        accountTxt_1.setColumns(10);
        //在面板上添加"密码："文本
        JLabel label_3 = new JLabel("密码：");
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_3.setBounds(196, 28, 54, 15);
        panel_1.add(label_3);
        //设置文本框
        passwordTxt = new JTextField();
        passwordTxt.setBounds(236, 25, 100, 21);
        panel_1.add(passwordTxt);
        passwordTxt.setColumns(10);
        //在面板上添加"身份证号："文本
        JLabel label_4 = new JLabel("身份证号：");
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_4.setBounds(362, 28, 75, 15);
        panel_1.add(label_4);
        //设置文本框
        idCardTxt = new JTextField();
        idCardTxt.setBounds(431, 25, 223, 21);
        panel_1.add(idCardTxt);
        idCardTxt.setColumns(10);
        //在面板上添加"姓名："文本
        JLabel label_5 = new JLabel("姓名：");
        label_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_5.setBounds(28, 70, 45, 15);
        panel_1.add(label_5);
        //设置文本框
        nameTxt_1 = new JTextField();
        nameTxt_1.setColumns(10);
        nameTxt_1.setBounds(68, 67, 100, 21);
        panel_1.add(nameTxt_1);
        //在面板上添加"性别："文本
        JLabel label_6 = new JLabel("性别：");
        label_6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_6.setBounds(196, 70, 45, 15);
        panel_1.add(label_6);
        //在面板上添加"年龄："文本
        JLabel label_7 = new JLabel("年龄：");
        label_7.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_7.setBounds(363, 70, 45, 15);
        panel_1.add(label_7);
        //设置文本框
        ageTxt = new JTextField();
        ageTxt.setColumns(10);
        ageTxt.setBounds(402, 67, 100, 21);
        panel_1.add(ageTxt);
        //在面板上添加"余额："文本
        JLabel lblNewLabel_1 = new JLabel("余额：");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(28, 112, 45, 15);
        panel_1.add(lblNewLabel_1);
        //设置文本框
        balanceTxt = new JTextField();
        balanceTxt.setColumns(10);
        balanceTxt.setBounds(68, 109, 100, 21);
        panel_1.add(balanceTxt);
        //设置"男"，"女"两个单选按钮,并定义按钮组
        manJrb = new JRadioButton("男");
        buttonGroup.add(manJrb);
        manJrb.setSelected(true);
        manJrb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        manJrb.setBounds(238, 65, 40, 23);
        panel_1.add(manJrb);
        womanJrb = new JRadioButton("女");
        buttonGroup.add(womanJrb);
        womanJrb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        womanJrb.setBounds(288, 65, 40, 23);
        panel_1.add(womanJrb);
        //设置删除按钮
        JButton button = new JButton("删除");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                cardDeleteActionPerformed(evt);
            }
        });
        button.setIcon(new ImageIcon(MainFrm.class.getResource("/images/删 除 .png")));
        button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        button.setBounds(554, 100, 100, 33);
        panel_1.add(button);
        //设置修改按钮
        JButton btnNewButton_1 = new JButton("修改");
        btnNewButton_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                cardUpdateActionPerformed(evt);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/修改.png")));
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnNewButton_1.setBounds(429, 100, 100, 33);
        panel_1.add(btnNewButton_1);
        //在面板上添加"账户添加"面板
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "账户添加", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(10, 416, 664, 199);
        contentPane.add(panel_2);
        panel_2.setLayout(null);
        //在面板上添加"账户："文本
        JLabel label_8 = new JLabel("账户：");
        label_8.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_8.setBounds(28, 49, 54, 15);
        panel_2.add(label_8);
        //设置文本框
        accountAddTxt = new JTextField();
        accountAddTxt.setColumns(10);
        accountAddTxt.setBounds(68, 46, 100, 21);
        panel_2.add(accountAddTxt);
        //在面板上添加"密码："文本
        JLabel label_9 = new JLabel("密码：");
        label_9.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_9.setBounds(196, 49, 54, 15);
        panel_2.add(label_9);
        //设置文本框
        passwordAddTxt = new JTextField();
        passwordAddTxt.setColumns(10);
        passwordAddTxt.setBounds(236, 46, 100, 21);
        panel_2.add(passwordAddTxt);
        //在面板上添加"身份证号："文本
        JLabel label_10 = new JLabel("身份证号：");
        label_10.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_10.setBounds(362, 49, 75, 15);
        panel_2.add(label_10);
        //设置文本框
        idCardAddTxt = new JTextField();
        idCardAddTxt.setColumns(10);
        idCardAddTxt.setBounds(431, 46, 223, 21);
        panel_2.add(idCardAddTxt);
        //在面板上添加"姓名："文本
        JLabel label_11 = new JLabel("姓名：");
        label_11.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_11.setBounds(28, 91, 45, 15);
        panel_2.add(label_11);
        //设置文本框
        nameAddTxt = new JTextField();
        nameAddTxt.setColumns(10);
        nameAddTxt.setBounds(68, 88, 100, 21);
        panel_2.add(nameAddTxt);
        //在面板上添加"性别："文本
        JLabel label_12 = new JLabel("性别：");
        label_12.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_12.setBounds(196, 91, 45, 15);
        panel_2.add(label_12);
        //设置"男"，"女"两个单选按钮,并定义按钮组
        manAddJrb = new JRadioButton("男");
        buttonGroup_1.add(manAddJrb);//定义按钮组
        manAddJrb.setSelected(true);
        manAddJrb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        manAddJrb.setBounds(238, 86, 40, 23);
        panel_2.add(manAddJrb);
        womanAddJrb = new JRadioButton("女");
        buttonGroup_1.add(womanAddJrb);
        womanAddJrb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        womanAddJrb.setBounds(288, 86, 40, 23);
        panel_2.add(womanAddJrb);
        //在面板上添加"年龄："文本
        JLabel label_13 = new JLabel("年龄：");
        label_13.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_13.setBounds(363, 91, 45, 15);
        panel_2.add(label_13);
        //设置文本框
        ageAddTxt = new JTextField();
        ageAddTxt.setColumns(10);
        ageAddTxt.setBounds(402, 88, 100, 21);
        panel_2.add(ageAddTxt);
        //在面板上添加"余额："文本
        JLabel label_14 = new JLabel("余额：");
        label_14.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label_14.setBounds(28, 133, 45, 15);
        panel_2.add(label_14);
        //设置文本框
        balanceAddTxt = new JTextField();
        balanceAddTxt.setColumns(10);
        balanceAddTxt.setBounds(68, 130, 100, 21);
        panel_2.add(balanceAddTxt);
        //设置添加按钮
        JButton button_1 = new JButton("添加");
        button_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                accountAddActionPerformed(evt);
            }
        });
        button_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        button_1.setBounds(554, 145, 100, 33);
        panel_2.add(button_1);
        //设置导出按钮并导出
        JButton btnExport = new JButton("导出");
        btnExport.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                exportActionPerformed(evt);
            }
        });
        btnExport.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5BFC\u51FA.png")));
        btnExport.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnExport.setBounds(574, 224, 100, 33);
        contentPane.add(btnExport);
        //调用fillTable方法填充JTable
        this.fillTable(new Card());
        //设置JFrame居中显示
        this.setLocationRelativeTo(null);
    }


    //导出事件
    private void exportActionPerformed(ActionEvent evt)
    {
        FileDialog fd = new FileDialog(this, "保存账户信息", FileDialog.SAVE);
        fd.setLocation(500,350);
        fd.setVisible(true);
        String stringfile = fd.getDirectory() + fd.getFile() + ".xls";
        ExcelExporter export = new ExcelExporter();
        try
        {
            export.exportTable(CardTable, new File(stringfile));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //账户添加事件
    private void accountAddActionPerformed(ActionEvent evt)
    {
        String account = this.accountAddTxt.getText();
        if(StringUtil.isEmpty(account))
        {
            JOptionPane.showMessageDialog(null, "账户号码不能为空！");
            return;
        }
        //获取文本框内容
        String password = this.passwordAddTxt.getText();
        String name = this.nameAddTxt.getText();
        String sex = "";
        if(this.manAddJrb.isSelected())
        {
            sex = "男";
        }else
        {
            sex = "女";
        }
        String age = this.ageAddTxt.getText();
        String idCard = this.idCardAddTxt.getText();
        String balance = this.balanceAddTxt.getText();
        //错误检测
        if(StringUtil.isEmpty(password))
        {
            JOptionPane.showMessageDialog(null, "账户密码不能为空！");
            return;
        }
        if(StringUtil.isEmpty(name))
        {
            JOptionPane.showMessageDialog(null, "姓名不能为空！");
            return;
        }
        if(StringUtil.isEmpty(idCard))
        {
            JOptionPane.showMessageDialog(null, "身份证号码不能为空！");
            return;
        }
        Card card = new Card(account, name, sex, age, password, idCard, balance);
        Connection con = null;
        try
        {
            con = dbUtil.getCon();//数据库建立连接
            //调用cardDao里查询账户方法
            if(cardDao.accountSearch(con, account) == true)
            {
                JOptionPane.showMessageDialog(null, "已存在此账户号码！请重新输入！");
                this.accountAddTxt.setText("");
                return;
            }
            //调用cardDao里的添加方法
            int add = cardDao.add(con, card);
            int add1 = cardDao.add1(con, card);
            if(add1 == 1)
            {
                this.fillTable(new Card());//向JTable内添加信息
                resetAddPanel();//调用重置账户添加面板方法
            }
            if(add == 1)
            {
                JOptionPane.showMessageDialog(null, "账户添加成功！");
                this.fillTable(new Card());//向JTable内添加信息
                resetAddPanel();//调用重置账户添加面板方法
            }else
            {
                JOptionPane.showMessageDialog(null, "账户添加失败！");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "账户添加失败！");
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
    //重置账户添加面板
    private void resetAddPanel()
    {
        this.accountAddTxt.setText("");
        this.passwordAddTxt.setText("");
        this.nameAddTxt.setText("");
        this.ageAddTxt.setText("");
        this.manAddJrb.setSelected(true);
        this.idCardAddTxt.setText("");
        this.balanceAddTxt.setText("");
    }
    //账户删除事件
    private void cardDeleteActionPerformed(ActionEvent evt)
    {
        String account = this.accountTxt_1.getText();
        if(StringUtil.isEmpty(account))
        {
            JOptionPane.showMessageDialog(null, "请选择要删除的账户!");
            return;
        }
        int sure = JOptionPane.showConfirmDialog(null, "确定要删除此账户吗？");
        if(sure == 0)
        {
            Connection con = null;
            try
            {
                con = dbUtil.getCon();//数据库建立连接
                //调用cardDao里的删除方法
                int delete = cardDao.delete(con, account);
                int delete1 = cardDao.delete1(con, account);
                if(delete1 == 1)
                {
                    resetValue();
                    fillTable(new Card());//向JTable内添加信息
                }
                if(delete == 1)
                {
                    JOptionPane.showMessageDialog(null, "账户删除成功！");
                    resetValue();
                    fillTable(new Card());//向JTable内添加信息
                }else
                {
                    JOptionPane.showMessageDialog(null, "账户删除失败！");
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "账户删除失败！");
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
    }
    //账户信息修改事件
    private void cardUpdateActionPerformed(ActionEvent evt)
    {
        String account = this.accountTxt_1.getText();
        if(StringUtil.isEmpty(account))
        {
            JOptionPane.showMessageDialog(null, "请选择要修改的账户！");
            return;
        }
        //获得文本框内数据
        String password = this.passwordTxt.getText();
        String name = this.nameTxt_1.getText();
        String sex = "";
        if(manJrb.isSelected())
        {
            sex = "男";
        }else {
            sex = "女";
        }
        String age = this.ageTxt.getText();
        String idCard = this.idCardTxt.getText();
        String balance = this.balanceTxt.getText();
        //错误检测
        if(StringUtil.isEmpty(password))
        {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return;
        }
        if(StringUtil.isEmpty(name))
        {
            JOptionPane.showMessageDialog(null, "姓名不能为空！");
            return;
        }
        if(StringUtil.isEmpty(idCard))
        {
            JOptionPane.showMessageDialog(null, "身份证号不能为空！");
            return;
        }

        Card card = new Card(account, name, sex, age, password, idCard, balance);
        Connection con = null;
        try
        {
            con = dbUtil.getCon();//数据库建立连接
            //调用cardDao里的修改方法
            int update = cardDao.update(con, card);
            if(update == 1)
            {
                JOptionPane.showMessageDialog(null, "账户信息修改成功！");
                this.fillTable(new Card());//向JTable内添加信息
                resetValue();
            }else
            {
                JOptionPane.showMessageDialog(null, "账户信息修改失败！");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "账户信息修改失败！");
        }finally
        {
            try
            {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //重置操作表单面板
    private void resetValue()
    {
        this.accountTxt_1.setText("");
        this.passwordTxt.setText("");
        this.nameTxt_1.setText("");
        this.manJrb.setSelected(true);
        this.ageTxt.setText("");
        this.idCardTxt.setText("");
        this.balanceTxt.setText("");
    }
    //表格行点击事件处理
    private void CardTableMousePressed(MouseEvent met)
    {
        int row = this.CardTable.getSelectedRow();
        this.accountTxt_1.setText((String)CardTable.getValueAt(row, 0));
        this.passwordTxt.setText((String)CardTable.getValueAt(row, 1));
        this.nameTxt_1.setText((String)CardTable.getValueAt(row, 2));
        String sex = (String)CardTable.getValueAt(row, 3);
        if("男".equals(sex))
        {
            this.manJrb.setSelected(true);
        }else
        {
            this.womanJrb.setSelected(true);
        }
        this.ageTxt.setText((String)CardTable.getValueAt(row, 4));
        this.idCardTxt.setText((String)CardTable.getValueAt(row, 5));
        this.balanceTxt.setText((String)CardTable.getValueAt(row, 6));
    }
    //查询事件处理
    private void cardSearchActionPerformed(ActionEvent evt)
    {
        String account = this.accountTxt.getText();
        String name = this.nameTxt.getText();
        String idCard = this.IDCardTxt.getText();
        Card card = new Card(account, name, idCard);
        this.fillTable(card);//向JTable内添加信息
    }
    //向JTable内添加信息
    private void fillTable(Card card)
    {
        DefaultTableModel dtm = (DefaultTableModel) CardTable.getModel();//添加列
        dtm.setRowCount(0); //设置成0行
        Connection con = null;
        try
        {
            con = dbUtil.getCon();
            ResultSet rs = cardDao.listAll(con, card);//rs存取card表内account所在的那一行
            while(rs.next())
            {
                Vector v = new Vector();
                v.add(rs.getString("account"));
                v.add(rs.getString("password"));
                v.add(rs.getString("name"));
                v.add(rs.getString("sex"));
                v.add(rs.getString("age"));
                v.add(rs.getString("idCard"));
                v.add(rs.getString("balance"));
                dtm.addRow(v);//向JTable中添加
            }
        }catch(Exception e)
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
}
