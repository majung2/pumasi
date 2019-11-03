package com.example.myapplication;

import android.provider.BaseColumns;

public class Databases {
    public static final class CreateDB implements BaseColumns {
        public static final String USERID = "userid";
        public static final String PATH = "path";
        public static final String _TABLENAME0 = "paths";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +PATH+" text not null);";
    }
}
