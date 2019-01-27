package ua.com.freearduino.myapplication.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ua.com.freearduino.myapplication.db.Resource;
import ua.com.freearduino.myapplication.db.dao.core.DAO;
import ua.com.freearduino.myapplication.model.User;

public class UserDAO implements DAO<User> {

    private SQLiteDatabase sqLiteDatabase;

    public UserDAO(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void save(User user) {
        sqLiteDatabase.insert(Resource.User.TABLE_NAME, null, userContentValues(user));
    }

    private ContentValues userContentValues(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Resource.User.ID, user.getId());
        contentValues.put(Resource.User.NAME, user.getName());
        contentValues.put(Resource.User.SURNAME, user.getSurname());
        contentValues.put(Resource.User.AGE, user.getAge());

        return contentValues;
    }

    @Override
    public List<User> getAll() {
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from "
                        + Resource.User.TABLE_NAME,
                null);

        return parceCursor(cursor);
    }

    @Override
    public List<User> parceCursor(Cursor cursor) {
        List<User> users = new ArrayList<>();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Resource.User.ID));
                String name = cursor.getString(cursor.getColumnIndex(Resource.User.NAME));
                String surname = cursor.getString(cursor.getColumnIndex(Resource.User.SURNAME));
                int age = cursor.getInt(cursor.getColumnIndex(Resource.User.AGE));

                users.add(new User(id, name, surname, age));
            } while (cursor.moveToNext());
        }

        if(cursor != null) {
            cursor.close();
        }

        return users;
    }
}