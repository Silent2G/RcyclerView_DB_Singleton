package ua.com.freearduino.myapplication.db;

public class Resource {
    public static final String DB_NAME = "db_eco_city_android";
    public static final int DB_VERSION = 1;


    public static final class User {
        public static final String TABLE_NAME = "user";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String SURNAME = "surname";

        public static final String CREATE_TABLE =
                "CREATE TABLE "
                        + TABLE_NAME
                        + " ("
                        + ID
                        + " INTEGER PRIMARY KEY, "
                        + NAME
                        + " TEXT(255), "
                        + SURNAME
                        + " TEXT(255), "
                        + AGE
                        + " INTEGER"
                        + ");";
    }
}
