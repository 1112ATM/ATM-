package com.model;

//���п�ʵ��
public class Card
{
    private String account; // �˺�
    private String name; // ����
    private String sex; // �Ա�
    private String age; // ����
    private String password; // ����
    private String idCard; // ���֤
    private String balance; // ���

    public Card()
    {
        super();
    }

    public Card(String account, String name, String sex, String age, String password, String idCard, String balance)
    {
        super();
        this.account = account;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.password = password;
        this.idCard = idCard;
        this.balance = balance;
    }

    public Card(String account, String name, String idCard)
    {
        super();
        this.account = account;
        this.name = name;
        this.idCard = idCard;
    }
    public Card(String account, String password)
    {
        super();
        this.account = account;
        this.password = password;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
    }

}
