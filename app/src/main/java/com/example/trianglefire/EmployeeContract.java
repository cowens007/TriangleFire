package com.example.trianglefire;

import android.provider.BaseColumns;

public final class EmployeeContract
{
    private EmployeeContract()
    {}
    public static class EmployeeNamesTable implements BaseColumns
    {
        public static final String TABLE_NAME = "employees";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_SEX = "sex";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_RELIGION = "religion";

    }

}
