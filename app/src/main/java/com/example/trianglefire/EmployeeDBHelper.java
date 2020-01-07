package com.example.trianglefire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.trianglefire.EmployeeContract.EmployeeNamesTable;

import java.util.ArrayList;
import java.util.List;


public class EmployeeDBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "TriangleFire.db";
    private static final int DATABASE_VERSION = 3;

    private SQLiteDatabase db;

    public EmployeeDBHelper( Context context)
    {
        super(context,DATABASE_NAME , null, DATABASE_VERSION );
    }
    @Override
    public void onCreate(@org.jetbrains.annotations.NotNull SQLiteDatabase db)
    {
        this.db = db;

        final String SQL_CREATE_EMPLOYEE_TABLE = "CREATE TABLE " +
                EmployeeContract.EmployeeNamesTable.TABLE_NAME + " ( " +
                EmployeeNamesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EmployeeNamesTable.COLUMN_LAST_NAME + " TEXT, " +
                EmployeeNamesTable.COLUMN_FIRST_NAME + " TEXT, " +
                EmployeeNamesTable.COLUMN_SEX + " TEXT, " +
                EmployeeNamesTable.COLUMN_AGE + " INTEGER, " +
                EmployeeNamesTable.COLUMN_RELIGION + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_EMPLOYEE_TABLE);
        fillEmployeeTable();
    }

    // Method to upgrade the table if more elements are added,
    // increment DATABASE_VERSION by 1 first
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeNamesTable.TABLE_NAME);

        //create tables again
        onCreate(db);
    }

    private void fillEmployeeTable()
    {
        Employees e1 = new Employees("Altman", "Anna", "F", 19, "Jewish");
        addEmployee(e1);
        Employees e2 = new Employees("Annino", "Arcito", "F", 25, "Catholic" );
        addEmployee(e2);
        Employees e3 = new Employees("Bono", "Norman", "M", 32, "Catholic");
        addEmployee(e3);
        Employees e4 = new Employees("Carson", "Ben", "M", 20, "Catholic");
        addEmployee(e4);
    }

    private void addEmployee (Employees employee)
    {
        ContentValues cv = new ContentValues();
        cv.put(EmployeeNamesTable.COLUMN_LAST_NAME, employee.getLastName());
        cv.put(EmployeeNamesTable.COLUMN_FIRST_NAME, employee.getFirstName());
        cv.put(EmployeeNamesTable.COLUMN_SEX, employee.getSex());
        cv.put(EmployeeNamesTable.COLUMN_AGE, employee.getAge());
        cv.put(EmployeeNamesTable.COLUMN_RELIGION, employee.getReligion());
        db.insert(EmployeeNamesTable.TABLE_NAME, null, cv);
    }

    public List<Employees> getAllEmployees()
    {
        List<Employees> employeesList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + EmployeeNamesTable.TABLE_NAME, null);

        if (c.moveToFirst())
        {
            do{
                Employees employee = new Employees();
                employee.setLastName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_LAST_NAME)));
                employee.setFirstName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_FIRST_NAME)));
                employee.setSex(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_SEX)));
                employee.setAge(c.getInt(c.getColumnIndex(EmployeeNamesTable.COLUMN_AGE)));
                employee.setReligion(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_RELIGION)));
                employeesList.add(employee);
            }
            while (c.moveToNext());
        }
        c.close();
        return employeesList;
    }
    public List<String> getLastNameAllEmployees()
    {
        List<String> lastNameList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + EmployeeNamesTable.TABLE_NAME, null);

        lastNameList.add("Employee List");
        if (c.moveToFirst())
        {
            do{
                Employees employee = new Employees();

                employee.setLastName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_LAST_NAME)));
                employee.setFirstName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_FIRST_NAME)));

                lastNameList.add(employee.getLastName()+", "+employee.getFirstName());
            }
            while (c.moveToNext());
        }
        c.close();
        return lastNameList;
    }
    public List<String> getSexFEmployees()
    {
        List<String> sexList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + EmployeeNamesTable.TABLE_NAME, null);

       sexList.add("Employee List");
        if (c.moveToFirst())
        {
            do{
                Employees employee = new Employees();

                employee.setSex(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_SEX)));
                employee.setLastName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_LAST_NAME)));
                employee.setFirstName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_FIRST_NAME)));

                if (employee.getSex().equals("F"))
                    sexList.add(employee.getLastName()+", "+employee.getFirstName());

            }
            while (c.moveToNext());
        }
        c.close();
        return sexList;
    }

    public List<String> getGenderEmployees(String genderChoice)
    {
        List<String> genderList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + EmployeeNamesTable.TABLE_NAME, null);

        genderList.add("Employee List");
        if (c.moveToFirst())
        {
            do{
                Employees employee = new Employees();

                employee.setSex(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_SEX)));
                employee.setLastName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_LAST_NAME)));
                employee.setFirstName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_FIRST_NAME)));

                if (employee.getSex().equals(genderChoice))
                    genderList.add(employee.getLastName()+", "+ employee.getFirstName());

            }
            while (c.moveToNext());
        }
        c.close();
        return genderList;
    }

    public List<String> getReligionEmployees(String religionChoice)
    {
        List<String> religionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + EmployeeNamesTable.TABLE_NAME, null);

        religionList.add("Employee List");
        if (c.moveToFirst())
        {
            do{
                Employees employee = new Employees();

                employee.setReligion(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_RELIGION)));
                employee.setLastName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_LAST_NAME)));
                employee.setFirstName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_FIRST_NAME)));

                if (employee.getReligion().equals(religionChoice))
                    religionList.add(employee.getLastName()+", "+ employee.getFirstName());

            }
            while (c.moveToNext());
        }
        c.close();
        return religionList;
    }


    public List<Employees> getOneEmployee(String name)
    {
        List<Employees> oneNameList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + EmployeeNamesTable.TABLE_NAME , null );

        if (c.moveToFirst())
        {
            do{
                Employees employee = new Employees();

                employee.setSex(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_SEX)));
                employee.setLastName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_LAST_NAME)));
                employee.setFirstName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_FIRST_NAME)));
                employee.setAge(c.getInt(c.getColumnIndex(EmployeeNamesTable.COLUMN_AGE)));
                employee.setReligion(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_RELIGION)));

                String findName = employee.getLastName() + ", " + employee.getFirstName();
                if (findName.equals(name))
                    oneNameList.add(employee);

            }
            while (c.moveToNext());
        }
        c.close();
        return oneNameList;
    }

    public List<String> getAgeEmployees(int lowRange, int highRange)
    {
        List<String> ageList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + EmployeeNamesTable.TABLE_NAME, null);

        ageList.add("Employee List");
        if (c.moveToFirst())
        {
            do{
                Employees employee = new Employees();

                employee.setAge(c.getInt(c.getColumnIndex(EmployeeNamesTable.COLUMN_AGE)));
                employee.setLastName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_LAST_NAME)));
                employee.setFirstName(c.getString(c.getColumnIndex(EmployeeNamesTable.COLUMN_FIRST_NAME)));

                if (employee.getAge()>=lowRange && employee.getAge()<=highRange)
                    ageList.add(employee.getLastName()+", "+employee.getFirstName() + "  age = " +employee.getAge());

            }
            while (c.moveToNext());
        }
        c.close();
        return ageList;
    }



}
