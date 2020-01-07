package com.example.trianglefire;

public class Employees
{
    private int id;
    private String lastName;
    private String firstName;
    private String sex;
    private int age;
    private String religion;


    public Employees()
    {

    }



    public Employees( String lastName, String firstName, String sex, int age, String religion)
    {
        //this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.age = age;
        this.religion = religion;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    };

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setAge (int age) {this.age = age;}

    public int getAge() {return age;}

    public void setReligion ( String religion){this.religion = religion;}

    public String getReligion() {return religion;}

    public Employees getEmployee()
    {return this;}
}
