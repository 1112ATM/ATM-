package com.model;

//银行卡实体
public class Card
{
    private String account; // 账号
    private String name; // 姓名
    private String sex; // 性别
    private String age; // 年龄
    private String password; // 密码
    private String idCard; // 身份证
    private String balance; // 余额

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
