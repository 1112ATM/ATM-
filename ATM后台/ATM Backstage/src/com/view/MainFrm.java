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
    //����Ӧ�ó���
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
    //�������
    public MainFrm(Admin admin)
    {
        setResizable(false); //�����޷�����
        setTitle("�û���Ϣ����");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //�رղ����� �������˳�
        setBounds(100, 100, 690, 654);
        contentPane = new JPanel();//ʵ����һ�����
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);//�ڱ�����CardQueryFrm�м������
        contentPane.setLayout(null);
        //��һ�����й����������
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 664, 150);
        contentPane.add(scrollPane);
        //��һ�������Ա�����ʽ��ʾ���û�
        CardTable = new JTable();
        CardTable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent met)
            {
                CardTableMousePressed(met);
            }
        });
        CardTable.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        CardTable.setModel(new DefaultTableModel(
                new Object[][] {},
                //����JTable������
                new String[]
                {
                        "�˺�", "����", "����", "�Ա�", "����", "���֤��", "���"
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
        //�Զ���JTable�еĿ��
        CardTable.getColumnModel().getColumn(2).setPreferredWidth(64);
        CardTable.getColumnModel().getColumn(3).setPreferredWidth(49);
        CardTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        CardTable.getColumnModel().getColumn(5).setPreferredWidth(91);
        CardTable.getColumnModel().getColumn(6).setPreferredWidth(90);
        scrollPane.setViewportView(CardTable);//�������������JTable
        //����������"��������"���
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "��������", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 10, 664, 52);
        contentPane.add(panel);
        panel.setLayout(null);
        //����������"�˺ţ�"�ı�
        JLabel lblNewLabel = new JLabel("�˺ţ�");
        lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        lblNewLabel.setBounds(18, 23, 51, 15);
        panel.add(lblNewLabel);
        //�����ı���
        accountTxt = new JTextField();
        accountTxt.setBounds(63, 20, 108, 21);
        panel.add(accountTxt);
        accountTxt.setColumns(10);
        //����������"������"�ı�
        JLabel label = new JLabel("������");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        label.setBounds(191, 23, 51, 15);
        panel.add(label);
        //�����ı���
        nameTxt = new JTextField();
        nameTxt.setColumns(10);
        nameTxt.setBounds(233, 20, 72, 21);
        panel.add(nameTxt);
        //���ò�ѯ��ť
        JButton btnNewButton = new JButton("��ѯ");
        btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cardSearchActionPerformed(e);
            }
        });
        btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        btnNewButton.setIcon(new ImageIcon(MainFrm.class.getResource("/images/��ѯ.png")));
        btnNewButton.setBounds(536, 10, 118, 33);
        panel.add(btnNewButton);
        //����������"���֤�ţ�"�ı�
        JLabel label_1 = new JLabel("���֤�ţ�");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        label_1.setBounds(326, 23, 80, 15);
        panel.add(label_1);
        //�����ı���
        IDCardTxt = new JTextField();
        IDCardTxt.setColumns(10);
        IDCardTxt.setBounds(403, 21, 123, 21);
        panel.add(IDCardTxt);
        //����������"������"���
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "������", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(10, 256, 664, 150);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        //����������"�˺ţ�"�ı�
        JLabel label_2 = new JLabel("�˺ţ�");
        label_2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_2.setBounds(28, 28, 54, 15);
        panel_1.add(label_2);
        //�����ı���
        accountTxt_1 = new JTextField();
        accountTxt_1.setEditable(false);
        accountTxt_1.setBounds(68, 25, 100, 21);
        panel_1.add(accountTxt_1);
        accountTxt_1.setColumns(10);
        //����������"���룺"�ı�
        JLabel label_3 = new JLabel("���룺");
        label_3.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_3.setBounds(196, 28, 54, 15);
        panel_1.add(label_3);
        //�����ı���
        passwordTxt = new JTextField();
        passwordTxt.setBounds(236, 25, 100, 21);
        panel_1.add(passwordTxt);
        passwordTxt.setColumns(10);
        //����������"���֤�ţ�"�ı�
        JLabel label_4 = new JLabel("���֤�ţ�");
        label_4.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_4.setBounds(362, 28, 75, 15);
        panel_1.add(label_4);
        //�����ı���
        idCardTxt = new JTextField();
        idCardTxt.setBounds(431, 25, 223, 21);
        panel_1.add(idCardTxt);
        idCardTxt.setColumns(10);
        //����������"������"�ı�
        JLabel label_5 = new JLabel("������");
        label_5.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_5.setBounds(28, 70, 45, 15);
        panel_1.add(label_5);
        //�����ı���
        nameTxt_1 = new JTextField();
        nameTxt_1.setColumns(10);
        nameTxt_1.setBounds(68, 67, 100, 21);
        panel_1.add(nameTxt_1);
        //����������"�Ա�"�ı�
        JLabel label_6 = new JLabel("�Ա�");
        label_6.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_6.setBounds(196, 70, 45, 15);
        panel_1.add(label_6);
        //����������"���䣺"�ı�
        JLabel label_7 = new JLabel("���䣺");
        label_7.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_7.setBounds(363, 70, 45, 15);
        panel_1.add(label_7);
        //�����ı���
        ageTxt = new JTextField();
        ageTxt.setColumns(10);
        ageTxt.setBounds(402, 67, 100, 21);
        panel_1.add(ageTxt);
        //����������"��"�ı�
        JLabel lblNewLabel_1 = new JLabel("��");
        lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(28, 112, 45, 15);
        panel_1.add(lblNewLabel_1);
        //�����ı���
        balanceTxt = new JTextField();
        balanceTxt.setColumns(10);
        balanceTxt.setBounds(68, 109, 100, 21);
        panel_1.add(balanceTxt);
        //����"��"��"Ů"������ѡ��ť,�����尴ť��
        manJrb = new JRadioButton("��");
        buttonGroup.add(manJrb);
        manJrb.setSelected(true);
        manJrb.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        manJrb.setBounds(238, 65, 40, 23);
        panel_1.add(manJrb);
        womanJrb = new JRadioButton("Ů");
        buttonGroup.add(womanJrb);
        womanJrb.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        womanJrb.setBounds(288, 65, 40, 23);
        panel_1.add(womanJrb);
        //����ɾ����ť
        JButton button = new JButton("ɾ��");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                cardDeleteActionPerformed(evt);
            }
        });
        button.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ɾ �� .png")));
        button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        button.setBounds(554, 100, 100, 33);
        panel_1.add(button);
        //�����޸İ�ť
        JButton btnNewButton_1 = new JButton("�޸�");
        btnNewButton_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                cardUpdateActionPerformed(evt);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/�޸�.png")));
        btnNewButton_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        btnNewButton_1.setBounds(429, 100, 100, 33);
        panel_1.add(btnNewButton_1);
        //����������"�˻����"���
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "�˻����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(10, 416, 664, 199);
        contentPane.add(panel_2);
        panel_2.setLayout(null);
        //����������"�˻���"�ı�
        JLabel label_8 = new JLabel("�˻���");
        label_8.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_8.setBounds(28, 49, 54, 15);
        panel_2.add(label_8);
        //�����ı���
        accountAddTxt = new JTextField();
        accountAddTxt.setColumns(10);
        accountAddTxt.setBounds(68, 46, 100, 21);
        panel_2.add(accountAddTxt);
        //����������"���룺"�ı�
        JLabel label_9 = new JLabel("���룺");
        label_9.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_9.setBounds(196, 49, 54, 15);
        panel_2.add(label_9);
        //�����ı���
        passwordAddTxt = new JTextField();
        passwordAddTxt.setColumns(10);
        passwordAddTxt.setBounds(236, 46, 100, 21);
        panel_2.add(passwordAddTxt);
        //����������"���֤�ţ�"�ı�
        JLabel label_10 = new JLabel("���֤�ţ�");
        label_10.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_10.setBounds(362, 49, 75, 15);
        panel_2.add(label_10);
        //�����ı���
        idCardAddTxt = new JTextField();
        idCardAddTxt.setColumns(10);
        idCardAddTxt.setBounds(431, 46, 223, 21);
        panel_2.add(idCardAddTxt);
        //����������"������"�ı�
        JLabel label_11 = new JLabel("������");
        label_11.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_11.setBounds(28, 91, 45, 15);
        panel_2.add(label_11);
        //�����ı���
        nameAddTxt = new JTextField();
        nameAddTxt.setColumns(10);
        nameAddTxt.setBounds(68, 88, 100, 21);
        panel_2.add(nameAddTxt);
        //����������"�Ա�"�ı�
        JLabel label_12 = new JLabel("�Ա�");
        label_12.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_12.setBounds(196, 91, 45, 15);
        panel_2.add(label_12);
        //����"��"��"Ů"������ѡ��ť,�����尴ť��
        manAddJrb = new JRadioButton("��");
        buttonGroup_1.add(manAddJrb);//���尴ť��
        manAddJrb.setSelected(true);
        manAddJrb.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        manAddJrb.setBounds(238, 86, 40, 23);
        panel_2.add(manAddJrb);
        womanAddJrb = new JRadioButton("Ů");
        buttonGroup_1.add(womanAddJrb);
        womanAddJrb.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        womanAddJrb.setBounds(288, 86, 40, 23);
        panel_2.add(womanAddJrb);
        //����������"���䣺"�ı�
        JLabel label_13 = new JLabel("���䣺");
        label_13.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_13.setBounds(363, 91, 45, 15);
        panel_2.add(label_13);
        //�����ı���
        ageAddTxt = new JTextField();
        ageAddTxt.setColumns(10);
        ageAddTxt.setBounds(402, 88, 100, 21);
        panel_2.add(ageAddTxt);
        //����������"��"�ı�
        JLabel label_14 = new JLabel("��");
        label_14.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        label_14.setBounds(28, 133, 45, 15);
        panel_2.add(label_14);
        //�����ı���
        balanceAddTxt = new JTextField();
        balanceAddTxt.setColumns(10);
        balanceAddTxt.setBounds(68, 130, 100, 21);
        panel_2.add(balanceAddTxt);
        //������Ӱ�ť
        JButton button_1 = new JButton("���");
        button_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                accountAddActionPerformed(evt);
            }
        });
        button_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        button_1.setBounds(554, 145, 100, 33);
        panel_2.add(button_1);
        //���õ�����ť������
        JButton btnExport = new JButton("����");
        btnExport.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                exportActionPerformed(evt);
            }
        });
        btnExport.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5BFC\u51FA.png")));
        btnExport.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        btnExport.setBounds(574, 224, 100, 33);
        contentPane.add(btnExport);
        //����fillTable�������JTable
        this.fillTable(new Card());
        //����JFrame������ʾ
        this.setLocationRelativeTo(null);
    }


    //�����¼�
    private void exportActionPerformed(ActionEvent evt)
    {
        FileDialog fd = new FileDialog(this, "�����˻���Ϣ", FileDialog.SAVE);
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
    //�˻�����¼�
    private void accountAddActionPerformed(ActionEvent evt)
    {
        String account = this.accountAddTxt.getText();
        if(StringUtil.isEmpty(account))
        {
            JOptionPane.showMessageDialog(null, "�˻����벻��Ϊ�գ�");
            return;
        }
        //��ȡ�ı�������
        String password = this.passwordAddTxt.getText();
        String name = this.nameAddTxt.getText();
        String sex = "";
        if(this.manAddJrb.isSelected())
        {
            sex = "��";
        }else
        {
            sex = "Ů";
        }
        String age = this.ageAddTxt.getText();
        String idCard = this.idCardAddTxt.getText();
        String balance = this.balanceAddTxt.getText();
        //������
        if(StringUtil.isEmpty(password))
        {
            JOptionPane.showMessageDialog(null, "�˻����벻��Ϊ�գ�");
            return;
        }
        if(StringUtil.isEmpty(name))
        {
            JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
            return;
        }
        if(StringUtil.isEmpty(idCard))
        {
            JOptionPane.showMessageDialog(null, "���֤���벻��Ϊ�գ�");
            return;
        }
        Card card = new Card(account, name, sex, age, password, idCard, balance);
        Connection con = null;
        try
        {
            con = dbUtil.getCon();//���ݿ⽨������
            //����cardDao���ѯ�˻�����
            if(cardDao.accountSearch(con, account) == true)
            {
                JOptionPane.showMessageDialog(null, "�Ѵ��ڴ��˻����룡���������룡");
                this.accountAddTxt.setText("");
                return;
            }
            //����cardDao�����ӷ���
            int add = cardDao.add(con, card);
            int add1 = cardDao.add1(con, card);
            if(add1 == 1)
            {
                this.fillTable(new Card());//��JTable�������Ϣ
                resetAddPanel();//���������˻������巽��
            }
            if(add == 1)
            {
                JOptionPane.showMessageDialog(null, "�˻���ӳɹ���");
                this.fillTable(new Card());//��JTable�������Ϣ
                resetAddPanel();//���������˻������巽��
            }else
            {
                JOptionPane.showMessageDialog(null, "�˻����ʧ�ܣ�");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "�˻����ʧ�ܣ�");
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
    //�����˻�������
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
    //�˻�ɾ���¼�
    private void cardDeleteActionPerformed(ActionEvent evt)
    {
        String account = this.accountTxt_1.getText();
        if(StringUtil.isEmpty(account))
        {
            JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����˻�!");
            return;
        }
        int sure = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�����˻���");
        if(sure == 0)
        {
            Connection con = null;
            try
            {
                con = dbUtil.getCon();//���ݿ⽨������
                //����cardDao���ɾ������
                int delete = cardDao.delete(con, account);
                int delete1 = cardDao.delete1(con, account);
                if(delete1 == 1)
                {
                    resetValue();
                    fillTable(new Card());//��JTable�������Ϣ
                }
                if(delete == 1)
                {
                    JOptionPane.showMessageDialog(null, "�˻�ɾ���ɹ���");
                    resetValue();
                    fillTable(new Card());//��JTable�������Ϣ
                }else
                {
                    JOptionPane.showMessageDialog(null, "�˻�ɾ��ʧ�ܣ�");
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "�˻�ɾ��ʧ�ܣ�");
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
    //�˻���Ϣ�޸��¼�
    private void cardUpdateActionPerformed(ActionEvent evt)
    {
        String account = this.accountTxt_1.getText();
        if(StringUtil.isEmpty(account))
        {
            JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��˻���");
            return;
        }
        //����ı���������
        String password = this.passwordTxt.getText();
        String name = this.nameTxt_1.getText();
        String sex = "";
        if(manJrb.isSelected())
        {
            sex = "��";
        }else {
            sex = "Ů";
        }
        String age = this.ageTxt.getText();
        String idCard = this.idCardTxt.getText();
        String balance = this.balanceTxt.getText();
        //������
        if(StringUtil.isEmpty(password))
        {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
            return;
        }
        if(StringUtil.isEmpty(name))
        {
            JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
            return;
        }
        if(StringUtil.isEmpty(idCard))
        {
            JOptionPane.showMessageDialog(null, "���֤�Ų���Ϊ�գ�");
            return;
        }

        Card card = new Card(account, name, sex, age, password, idCard, balance);
        Connection con = null;
        try
        {
            con = dbUtil.getCon();//���ݿ⽨������
            //����cardDao����޸ķ���
            int update = cardDao.update(con, card);
            if(update == 1)
            {
                JOptionPane.showMessageDialog(null, "�˻���Ϣ�޸ĳɹ���");
                this.fillTable(new Card());//��JTable�������Ϣ
                resetValue();
            }else
            {
                JOptionPane.showMessageDialog(null, "�˻���Ϣ�޸�ʧ�ܣ�");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "�˻���Ϣ�޸�ʧ�ܣ�");
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
    //���ò��������
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
    //����е���¼�����
    private void CardTableMousePressed(MouseEvent met)
    {
        int row = this.CardTable.getSelectedRow();
        this.accountTxt_1.setText((String)CardTable.getValueAt(row, 0));
        this.passwordTxt.setText((String)CardTable.getValueAt(row, 1));
        this.nameTxt_1.setText((String)CardTable.getValueAt(row, 2));
        String sex = (String)CardTable.getValueAt(row, 3);
        if("��".equals(sex))
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
    //��ѯ�¼�����
    private void cardSearchActionPerformed(ActionEvent evt)
    {
        String account = this.accountTxt.getText();
        String name = this.nameTxt.getText();
        String idCard = this.IDCardTxt.getText();
        Card card = new Card(account, name, idCard);
        this.fillTable(card);//��JTable�������Ϣ
    }
    //��JTable�������Ϣ
    private void fillTable(Card card)
    {
        DefaultTableModel dtm = (DefaultTableModel) CardTable.getModel();//�����
        dtm.setRowCount(0); //���ó�0��
        Connection con = null;
        try
        {
            con = dbUtil.getCon();
            ResultSet rs = cardDao.listAll(con, card);//rs��ȡcard����account���ڵ���һ��
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
                dtm.addRow(v);//��JTable�����
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
